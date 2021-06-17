package com.example.swp_dongnae;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.swp_dongnae.R.id.btn_call;

public class Info extends Fragment {
    private View view;

    // 정보페이지에 필요한 정보들 변수로 선언
    private String activity;
    private String purpose;
    private String captain;
    private String category;
    private String email;
    private String tel;
    private Button btn_call; //전화 걸기 버튼 추가


    public static Info newinstance() {
        Info infoinfo = new Info();
        return infoinfo;
    }


    //정보 페이지 화면 세팅
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.info, container, false);

        TextView tv_activity = (TextView) view.findViewById(R.id.tv_act);
        TextView tv_purpose = (TextView) view.findViewById(R.id.tv_pur);
        TextView tv_captain = (TextView) view.findViewById(R.id.tv_cap);
        TextView tv_category = (TextView) view.findViewById(R.id.tv_cat);
        TextView tv_email = (TextView) view.findViewById(R.id.tv_email);
        TextView tv_tell = (TextView) view.findViewById(R.id.tv_tel);
        Button btn_call = (Button)view.findViewById(R.id.btn_call);

        //카테고리 액티비티 클래스에서 값 가져오기
        String pos = getActivity().getIntent().getStringExtra("pos");
        String clubPos = getActivity().getIntent().getStringExtra("clubPos");


        //파이어베이스 데이터 베이스 연동 및 db 테이블 연결화
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("동아리").child(pos).child(clubPos);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Bs bs = new Bs(activity, purpose, captain, category, email, tel); // bs 객체를 불러온다.

                //bs객체에 데이터베이스값 설정
                bs.setActivity(dataSnapshot.child("activity").getValue().toString());
                bs.setPurpose(dataSnapshot.child("purpose").getValue().toString());
                bs.setCaptain(dataSnapshot.child("captain").getValue().toString());
                bs.setCategory(dataSnapshot.child("category").getValue().toString());
                bs.setEmail(dataSnapshot.child("email").getValue().toString());
                bs.setTel(dataSnapshot.child("tel").getValue().toString());

                //xml에 데이터베이스 값 설정
                tv_activity.setText(bs.getActivity());
                tv_purpose.setText(bs.getPurpose());
                tv_captain.setText(bs.getCaptain());
                tv_category.setText(bs.getCategory());
                tv_email.setText(bs.getEmail());
                tv_tell.setText(bs.getTel());




            }


            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        btn_call.setOnClickListener(new View.OnClickListener() { //전화 걸기 버튼 클릭시
            @Override
            public void onClick(View v) {
                String tell = tv_tell.getText().toString(); //전화번호를 string 형태로 받아옴
                Intent intent1 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"  + tell)); //다이얼 화면으로 화면전화 후 받아온 전화번호 입력
                startActivity(intent1);
                }
            });


        return view; //해당 뷰 출력
    }

}
