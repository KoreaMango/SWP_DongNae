package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClubNameActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ClubNameAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ClubNameSub> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String v_categoryName;
    private TextView categoryName;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_name);
        categoryName = findViewById(R.id.categoryName);
        recyclerView = findViewById(R.id.recyclerView); // 리사이클러뷰 아이디 가져오기
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 카테고리 액티비티 클래스의 객체를 담을 어레이 리스트


        String pos = getIntent().getStringExtra("pos"); // 포지션 이름 가져오기
        int posInt = getIntent().getIntExtra("posInt",0); // 앞 액티비티에서 선택된 포지션 값

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference("동아리"); //db 테이블 연결화
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { // 데이터 베이스 레퍼런스의 리스너가 한번만 실행됨
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는곳
                arrayList.clear(); //기존 배열리스트 초기화

                SwitchTool switchTool = new SwitchTool(); // 스위치 객체 생성
                v_categoryName = switchTool.switchClub(posInt); // 앞 액티비티에서 선택된 포지션 값으로 분과 가져오기
                categoryName.setText(v_categoryName);

                for (DataSnapshot snapshot : dataSnapshot.child(pos).getChildren()) {  //반복문으로 데이터 리스트를 추출, 앞에서 가져온 분과로 검색
                    ClubNameSub clubNameSub = snapshot.getValue(ClubNameSub.class); // ClubNameSub 객체의 필드 값에 데이터 넣기
                    Log.v("123",clubNameSub.getId()); // 값 잘 들고 오는지 확인
                    arrayList.add(clubNameSub); // 어레이 리스트에 clubNameSub 객체 넣기

                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("123", String.valueOf(databaseError.toException()));   //에러문 출력
            }
        });

        adapter = new ClubNameAdapter(arrayList, this); // 어댑터 생성
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
        adapter.setOnItemClickListener(new OnNameItemClickListener() {
            @Override
            public void onItemClick(ClubNameAdapter.ClubNameViewHolder holder, View view, int position) { // 리사이클러뷰 선택 시
                int itemPosition = recyclerView.getChildAdapterPosition(view); // 선택된 리사이클러뷰 포지션 담은 변수
                String clubPositon = Integer.toString(itemPosition); // 변수 String 값으로 바꿔주기


                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() { // 데이터 베이스 한번 연결
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //파이어베이스 데이터베이스의 데이터를 받아오는곳
                        String clubName; // 전 액티비티에서 선택한 분과 이름 변수
                        clubName = dataSnapshot.child(pos).child(clubPositon).child("id").getValue().toString(); // 전 액티비티에서 선택한 분과 이름 가져오기
                        String nickName = getIntent().getStringExtra("nickName"); // 로그인 이름 정보 가져오기
                        Intent intent2 = new Intent(ClubNameActivity.this, ViewClub.class); // 다음으로 넘겨줄 인텐트 생성
                        intent2.putExtra("club",clubName); // 전 액티비티에서 선택한 분과 이름 변수 넘겨주기
                        intent2.putExtra("pos",pos); // 앞에서 가져온 인덱스 값 다시 보내주기
                        intent2.putExtra("clubPos",clubPositon); // 앞에서 가져온 인덱스 값 다시 보내주기
                        intent2.putExtra("nickName",nickName); // 유저 이름 보내주기
                        Log.v("123",clubName);
                        startActivity(intent2); //액티비티 이동
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("01077368247", String.valueOf(databaseError.toException()));   //에러문 출력
                    }
                });


            }
        });

    }



}