package com.example.swp_dongnae;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QnA extends Fragment {
    private View view;

    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private ArrayList<NoticeSub> arrayList;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public static QnA newinstance() {
        QnA qa = new QnA();
        return qa;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.qna, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.qna_rv); //TODO 리사이클 뷰 아이디 연
        recyclerView.setHasFixedSize(true); //리사이클러뷰 성능 강화
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // 카테고리 액티비티 클래스의 객체를 담을 어레이 리스트

        String pos = getActivity().getIntent().getStringExtra("pos");
        String clubPos = getActivity().getIntent().getStringExtra("clubPos");




        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference("동아리").child(pos).child(clubPos).child("게시글").child("QnA"); //db 테이블 연결화


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는곳
                arrayList.clear(); //기존 배열리스트 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {  //반복문으로 데이터 리스트를 추출
                    Log.v("016",snapshot.getValue().toString() + "연동연동");
                    NoticeSub noticeSub = snapshot.getValue(NoticeSub.class);
                    Log.v("011",noticeSub.getDate()+ "연동연동");
                    Log.v("011",noticeSub.getDes()+ "연동연동");
                    Log.v("011",noticeSub.getUser()+ "연동연동");

                    arrayList.add(noticeSub);

                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", String.valueOf(databaseError.toException()));   //에러문 출력
            }
        });

        adapter = new NoticeAdapter(arrayList, context);
        RecyclerDecoration spaceDecoration = new RecyclerDecoration(20);
        recyclerView.addItemDecoration(spaceDecoration);

        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결
        adapter.setOnItemClickListener(new OnNoticeItemClickListener() {
            @Override
            public void onItemClick(NoticeAdapter.NoticeViewHolder holder, View view, int position) {
                NoticeSub item = adapter.getItem(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("bdh", "bdh");
                startActivity(intent);//액티비티 이동
            }
        });
        return view;
    }
}
