package com.example.swp_dongnae;
//dd
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Info extends Fragment {
    private View view;

    private String activity;
    private String purpose;
    private String captain;
    private String category;
    private String email;
    private String tel;


    public static Info newinstance() {
        Info infoinfo = new Info();
        return infoinfo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.info, container, false);

        TextView tv_activity = (TextView) view.findViewById(R.id.tv_act);
        TextView tv_purpose = (TextView) view.findViewById(R.id.tv_pur);
        TextView tv_captain = (TextView) view.findViewById(R.id.tv_cap);
        TextView tv_category = (TextView) view.findViewById(R.id.tv_cat);
        TextView tv_email = (TextView) view.findViewById(R.id.tv_email);
        TextView tv_tel = (TextView) view.findViewById(R.id.tv_tel);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("동아리").child("공연 분과").child("기라성");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Intent intent = new Intent(getActivity(), ViewClub.class);
                Bs bs = new Bs(activity, purpose, captain, category, email, tel);

                bs.setActivity(dataSnapshot.child("activity").getValue().toString());
                bs.setPurpose(dataSnapshot.child("purpose").getValue().toString());
                bs.setCaptain(dataSnapshot.child("captain").getValue().toString());
                bs.setCategory(dataSnapshot.child("category").getValue().toString());
                bs.setEmail(dataSnapshot.child("email").getValue().toString());
                bs.setTel(dataSnapshot.child("tel").getValue().toString());

                tv_activity.setText(bs.getActivity());
                tv_purpose.setText(bs.getPurpose());
                tv_captain.setText(bs.getCaptain());
                tv_category.setText(bs.getCategory());
                tv_email.setText(bs.getEmail());
                tv_tel.setText(bs.getTel());

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        return view;
    }
}
