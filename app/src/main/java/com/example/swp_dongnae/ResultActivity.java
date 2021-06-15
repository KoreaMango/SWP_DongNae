package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class ResultActivity extends AppCompatActivity {
    private Button btn_continue; //계속하기 버튼
    private Button logout_g; //로그아웃버튼
    private TextView tv_result;
    private ImageView iv_profile;

   

    @Override
    protected void onCreate(Bundle savedInstanceState) { //레이아웃을 생성하고 초기화 컴포넌트를 가져옴
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent =getIntent(); //인텐트 작업
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");

        tv_result= findViewById(R.id.tv_result);
        tv_result.setText(nickName);

        iv_profile= findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);

        logout_g = findViewById(R.id.logout_g);
        logout_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //버튼 클릭시 로그아웃
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) { //요청을 보내 인텐트 작업 활성화 및 작업 시작
                        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, 101);
                        finish();
                        return null;
                    }
                });
            }
        });

        btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,SelectActivity.class);
                intent.putExtra("nickName",nickName);
                startActivity(intent);//액티비티 이동

            }
        });


    }
}