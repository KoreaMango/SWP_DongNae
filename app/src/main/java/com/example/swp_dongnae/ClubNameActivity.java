package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClubNameActivity extends AppCompatActivity {
    private ListView clubListView;
    private ClubNameAdapter adapter;
    private List<ClubNameSub> clubList;

    String textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_name);

        clubListView = (ListView) findViewById(R.id.clubListView);
        clubList = new ArrayList<ClubNameSub>();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clubList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    textView = dataSnapshot.child(snapshot.getKey()).child("id").getValue().toString();
                    //imageView = dataSnapshot.child(snapshot.getKey()).child("author").getValue().toString();
                    clubList.add(new ClubNameSub(textView));
                }
                Collections.reverse(clubList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        showDetail();  // 클릭 시 공지사항 들어가기
        clubListView.setAdapter(adapter);  // 리스트뷰 세팅 하기

    }

    public void showDetail(){
        adapter = new ClubNameAdapter(getApplicationContext(),clubList);  // 임시
        clubListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ClubNameActivity.this, ClubActivity.class);
                intent.putExtra("idx",clubList.get(position).textView.toString());
                startActivity(intent);
            }
        });
    }
}