package com.example.swp_dongnae;


import android.os.Bundle;
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

public class Activity extends Fragment { // 세부 동아리의 활동 액티비티
    private RecyclerView recyclerView;
    private View view;
    private ArrayList<Bs> arrayList;
    private MediaAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public static Activity newinstance() {
        Activity activity = new Activity();
        return activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity, container, false);

        arrayList = new ArrayList<>(); // 카테고리 액티비티 클래스의 객체를 담을 어레이 리스트
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_media);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //카테고리 액티비티 클래스에서 값 가져오기
        String pos = getActivity().getIntent().getStringExtra("pos");
        String clubPos = getActivity().getIntent().getStringExtra("clubPos");

        //파이어베이스 데이터 베이스 연동 및 db 테이블 연결화
        final DatabaseReference reference_media = FirebaseDatabase.getInstance().getReference().child("동아리").child(pos).child(clubPos);
        reference_media.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear(); //기존 배열리스트 초기화
                for(DataSnapshot snapshot : datasnapshot.child("media").getChildren()) { //반복문으로 데이터 리스트를 추출
                    Bs bs = new Bs();
                    bs.setMedia(snapshot.getValue().toString()); // 이미지의 값 전달
                    arrayList.add(bs); // 어레이 리스트에 이미지 추가
                }
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        adapter = new MediaAdapter(arrayList, getContext()); //리스트 저장 및 새로고침
        recyclerView.setAdapter(adapter); //리사이클러뷰에 어댑터 연결

        return view;
    }

}
