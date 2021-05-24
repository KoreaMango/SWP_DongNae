package com.example.swp_dongnae;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListView repleListView;
    private RepleListAdapter adapter;
    private List<Reple> repleList;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    String repleUser;
    String repleDescription;
    TextView noticeTitle;
    TextView noticeUser;
    TextView noticeDay;
    TextView noticeDes;

    static int count = 0;

    Button put;
    EditText reple;
    String repleDes;
    String replePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String nickName = getIntent().getStringExtra("nickName");
        put = findViewById(R.id.putRepleBtn);
        reple = findViewById(R.id.et_reple);

        noticeDay = findViewById(R.id.tv_noticeDay);
        noticeDes = findViewById(R.id.tv_noticeDes);
        noticeUser = findViewById(R.id.tv_noticeUser);
        noticeTitle = findViewById(R.id.tv_noticeTitle);

        repleListView = (ListView) findViewById(R.id.repleListView);
        repleList = new ArrayList<Reple>();

        String pos = getIntent().getStringExtra("pos");
        String clubPos = getIntent().getStringExtra("clubPos");
        String type = getIntent().getStringExtra("type");
        String noticePosition = getIntent().getStringExtra("noticePosition");
        replePosition = noticePosition;

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference("동아리").child(pos).child(clubPos).child("게시글").child(type); //db 테이블 연결화

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count = 0;
                noticeDay.setText(dataSnapshot.child(noticePosition).child("date").getValue().toString());
                noticeDes.setText(dataSnapshot.child(noticePosition).child("des").getValue().toString());
                noticeUser.setText(dataSnapshot.child(noticePosition).child("user").getValue().toString());
                noticeTitle.setText(dataSnapshot.child(noticePosition).child("title").getValue().toString());

                repleList.clear();

                for (DataSnapshot snapshot : dataSnapshot.child(noticePosition).child("rep").getChildren()) {

                    repleDescription = dataSnapshot.child(noticePosition).child("rep").child(snapshot.getKey()).child("des").getValue().toString();
                    repleUser = dataSnapshot.child(noticePosition).child("rep").child(snapshot.getKey()).child("user").getValue().toString();
                    repleList.add(new Reple(repleUser, repleDescription));
                    count++;
                }
                Collections.reverse(repleList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        adapter = new RepleListAdapter(getApplicationContext(), repleList);
        repleListView.setAdapter(adapter);

        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                repleDes = reple.getText().toString();
                if (repleDes.isEmpty()) {
                    Log.v("7777","실패");
                }
                else{

                    writeNewReple(repleDes);
                    Log.v("7777","실행");
                }
            }
        });

    }

    private void writeNewReple(String des) {

        String noticeCount = Integer.toString(count);
        databaseReference.child(replePosition).child("rep").child(noticeCount).child("user").setValue("NULL");
        databaseReference.child(replePosition).child("rep").child(noticeCount).child("des").setValue(des);

        count = 0;
        Toast.makeText(getApplicationContext(),"댓글이 입력되었습니다.",Toast.LENGTH_SHORT).show(); // 토스트 메세지를 이용해 사용자에게 알림

    }

}