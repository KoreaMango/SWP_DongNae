package com.example.swp_dongnae;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Info extends Fragment {
    private View view;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<Bs> arrayList;
    private RecyclerView recyclerView222;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager222;

    private String v_info;

    public static Info newinstance() {
        Info infoinfo = new Info();
        return infoinfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.info, container, false);

        recyclerView222 = view.findViewById(R.id.recyclerView222);
        recyclerView222.setHasFixedSize(true);
        recyclerView222.setLayoutManager(layoutManager222);
        layoutManager222 = new LinearLayoutManager(getActivity());
        recyclerView222.setLayoutManager(layoutManager222);
        arrayList = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("동아리");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                arrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    Bs bs = snapshot1.getValue(Bs.class);
                    arrayList.add(bs);
                }
            adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Info", String.valueOf(error.toException()));
            }
        });

        adapter = new BsAdapter(arrayList, getActivity());
        recyclerView222.setAdapter(adapter);

        return view;
    }
}
