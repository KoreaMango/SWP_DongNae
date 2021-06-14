package com.example.swp_dongnae;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ListView repleListView; // 댓글 리스트 뷰
    private RepleListAdapter adapter; // 리스트뷰 어댑터
    private List<Reple> repleList; // 댓글 리스트
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    String repleUser; // 댓글 작성자
    String repleDescription; // 댓글 내용
    TextView noticeTitle; // 게시글 제목
    TextView noticeUser; // 게시글 작성자
    TextView noticeDay; // 게시글 작성날짜
    TextView noticeDes; // 게시글 내용
    Button put; // 댓글 입력 버튼
    EditText reple; // 댓글 입력 상자
    String repleDes; // 댓글 내용
    String replePosition; // 댓글 포지션
    static int count = 0; // 댓글 개수



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // xml 아이디 연결
        put = findViewById(R.id.putRepleBtn);
        reple = findViewById(R.id.et_reple);
        noticeDay = findViewById(R.id.tv_noticeDay);
        noticeDes = findViewById(R.id.tv_noticeDes);
        noticeUser = findViewById(R.id.tv_noticeUser);
        noticeTitle = findViewById(R.id.tv_noticeTitle);
        repleListView = (ListView) findViewById(R.id.repleListView);
        // 끝


        repleList = new ArrayList<Reple>(); // 댓글 리스트 객체 생성

        // 파이어베이스 연결을 위해 앞에서 가져온 값들
        String pos = getIntent().getStringExtra("pos");
        String clubPos = getIntent().getStringExtra("clubPos");
        String type = getIntent().getStringExtra("type");
        String noticePosition = getIntent().getStringExtra("noticePosition");
        String nickName = getIntent().getStringExtra("nickName");
        // 끝

        replePosition = noticePosition; // 댓글 데이터베이스에서 사용될 포지션

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference("동아리").child(pos).child(clubPos).child("게시글").child(type); //db 테이블 연결화

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { // 1번 동기화 시키고 리슨 안함
            // 이 부분을 비동기로 고쳐야하는데 비동기로 고치니 오류가 있었음.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // 앞에서 입력한 값들 데이터 베이스에서 들고와 setText 하는 부분
                noticeDay.setText(dataSnapshot.child(noticePosition).child("date").getValue().toString());
                noticeDes.setText(dataSnapshot.child(noticePosition).child("des").getValue().toString());
                noticeUser.setText(dataSnapshot.child(noticePosition).child("user").getValue().toString());
                noticeTitle.setText(dataSnapshot.child(noticePosition).child("title").getValue().toString());
                // 끝

                repleList.clear(); // 댓글 리스트 초기화
                count = 0; // 댓글 개수 초기화
                for (DataSnapshot snapshot : dataSnapshot.child(noticePosition).child("rep").getChildren()) { // 댓글 개수 만큼 반복
                    // 댓글 가져오는 코드
                    repleDescription = dataSnapshot.child(noticePosition).child("rep").child(snapshot.getKey()).child("des").getValue().toString();
                    repleUser = dataSnapshot.child(noticePosition).child("rep").child(snapshot.getKey()).child("user").getValue().toString();
                    // 끝
                    repleList.add(new Reple(repleUser, repleDescription)); // 리스트에 객체 넣기
                    count++; // 댓글 하나 읽었으니 count 증가
                }
                Collections.reverse(repleList); // 댓글 순서 뒤집기 , 이유 : 가장 최근 댓글이 위에 뜨기 위해서
                adapter.notifyDataSetChanged(); // 리스트뷰 새로고침
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        adapter = new RepleListAdapter(getApplicationContext(), repleList); // 댓글 어댑터 객체 생성
        repleListView.setAdapter(adapter); // 댓글 리스트뷰에 어댑터 연결

        put.setOnClickListener(new View.OnClickListener() { // 버튼 클릭 시
            @Override
            public void onClick(View v) {
                repleDes = reple.getText().toString(); // 댓글 입력 상자에 값 들고옴
                if (repleDes.isEmpty()) { // 댓글 입력 상자에 값이 없을 때
                    AlertDialog.Builder ad = new AlertDialog.Builder(DetailActivity.this); // 알림창 객체 생성
                    ad.setTitle("글쓰기 실패"); // 알림창 제목
                    ad.setMessage("작성하지 않은 항목이 있습니다."); // 알림창 내용
                    ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        } // 클릭 시 알림창 닫힘
                    });
                    ad.show(); // 알림창 보여주기
                }
                else{
                    writeNewReple(repleDes,nickName); // 댓글 생성 메소드
                    //TODO adapter.notifyDataSetChanged(); 버튼 클릭 시 댓글 어댑터 새로고
                    Log.v("7777","실행");
                }
            }
        });

    }

    private void writeNewReple(String des, String nick) {

        String noticeCount = Integer.toString(count); // 댓글 개수 변수
        databaseReference.child(replePosition).child("rep").child(noticeCount).child("user").setValue(nick); // 매개변수 값으로 작성자 데이터베이스에 입력
        databaseReference.child(replePosition).child("rep").child(noticeCount).child("des").setValue(des); // 매개변수 값으로 내용 데이터베이스에 입력

        count = 0; // 댓글 개수 초기화
        Toast.makeText(getApplicationContext(),"댓글이 입력되었습니다.",Toast.LENGTH_SHORT).show(); // 토스트 메세지를 이용해 사용자에게 알림

    }

}