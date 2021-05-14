package com.example.swp_dongnae;

import android.os.Bundle;
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

public class DetailActivity extends AppCompatActivity {
    private ListView repleListView;
    private RepleListAdapter adapter;
    private List<Reple> repleList;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;

    String repleUser;
    String repleDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        repleListView = (ListView) findViewById(R.id.repleListView);
        repleList = new ArrayList<Reple>();

        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference("게시글" ); //db 테이블 연결화

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                repleList.clear();;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    repleUser = dataSnapshot.child(snapshot.getKey()).child("User").getValue().toString();
                    repleDescription = dataSnapshot.child(snapshot.getKey()).child("Des").getValue().toString();
                    repleList.add(new Reple( repleUser, repleDescription));
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
    }
}