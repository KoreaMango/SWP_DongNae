package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

import static android.app.Activity.RESULT_OK;

public class QnA extends Fragment { // 세부 동아리의 QNA 액티비티
    static final int REQ_ADD_CONTACT = 1 ; // 상수 선언 TRUE
    private View view;

    private RecyclerView recyclerView;
    private NoticeAdapter adapter;
    private ArrayList<NoticeSub> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    static int count = 0;

    public static QnA newinstance() {
        QnA qa = new QnA();
        return qa;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.qna, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.qna_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList = new ArrayList<>(); // 카테고리 액티비티 클래스의 객체를 담을 어레이 리스트

        String pos = getActivity().getIntent().getStringExtra("pos");
        String clubPos = getActivity().getIntent().getStringExtra("clubPos");
        String nickName = getActivity().getIntent().getStringExtra("nickName");


        database = FirebaseDatabase.getInstance(); //파이어베이스 데이터 베이스 연동
        databaseReference = database.getReference("동아리").child(pos).child(clubPos).child("게시글").child("QnA"); //db 테이블 연결화

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = 0;
                arrayList.clear(); //기존 배열리스트 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {  //반복문으로 데이터 리스트를 추출
                    Log.v("016", snapshot.getValue().toString() + "연동연동");
                    NoticeSub noticeSub = snapshot.getValue(NoticeSub.class);
                    Log.v("011", noticeSub.getDate() + "연동연동");
                    Log.v("011", noticeSub.getDes() + "연동연동");
                    Log.v("011", noticeSub.getUser() + "연동연동");
                    count++;
                    arrayList.add(noticeSub);

                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        adapter = new NoticeAdapter(arrayList, view.getContext());
        RecyclerDecoration spaceDecoration = new RecyclerDecoration(20); // 리사이클러뷰 간격
        recyclerView.addItemDecoration(spaceDecoration);
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        /* 버튼을 클릭해 게시글을 작성하는 부분*/
        Button writeButton;
        writeButton = (Button) view.findViewById(R.id.writeBtn);
        writeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),WriteActivity.class);
                intent.putExtra("flag","qna");
                intent.putExtra("nickName",nickName);
                startActivityForResult(intent,REQ_ADD_CONTACT);
            }
        });



        // 리사이클러뷰 클릭 이벤트
        adapter.setOnItemClickListener(new OnNoticeItemClickListener() {
            @Override
            public void onItemClick(NoticeAdapter.NoticeViewHolder holder, View view, int position) {

                int itemPosition = recyclerView.getChildAdapterPosition(view);
                String noticePosition = Integer.toString(itemPosition);


                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("type", "QnA");
                intent.putExtra("noticePosition", noticePosition);
                intent.putExtra("pos", pos);
                intent.putExtra("clubPos", clubPos);
                intent.putExtra("nickName",nickName);

                startActivity(intent);//액티비티 이동
            }
        });
        return view;
    }

    // 게시글 작성 메소드
    private void writeNewNotice(String des, String user, String date, String title) {
        NoticeSub noticeSub = new NoticeSub(des, user, date, title);
        String noticePosition = Integer.toString(count);
        databaseReference.child(noticePosition).setValue(noticeSub);
        count = 0;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD_CONTACT) {
            if (resultCode == RESULT_OK) {
                String des;
                String user;
                String date;
                String title;

                title = data.getStringExtra("qna1");
                date = data.getStringExtra("qna2");
                user = data.getStringExtra("qna3");
                des = data.getStringExtra("qna4");
                Log.v("9999", title + date + user + des);

                writeNewNotice(des, user, date, title);


            }
        }
    }
}
