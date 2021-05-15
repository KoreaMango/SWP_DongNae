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

public class Activity extends Fragment {
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

        arrayList = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_media);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        String pos = getActivity().getIntent().getStringExtra("pos");
        String clubPos = getActivity().getIntent().getStringExtra("clubPos");

        final DatabaseReference reference_media = FirebaseDatabase.getInstance().getReference().child("동아리").child(pos).child(clubPos);
        reference_media.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                arrayList.clear();
                for(DataSnapshot snapshot : datasnapshot.child("media").getChildren()) {
                    Bs bs = new Bs();
                    bs.setMedia(snapshot.getValue().toString());
                    arrayList.add(bs);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });
        adapter = new MediaAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
