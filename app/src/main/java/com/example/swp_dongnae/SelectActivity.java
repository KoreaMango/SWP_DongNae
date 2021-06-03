package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectActivity extends AppCompatActivity { // 동아리 찾기 or 앱 사용 설명서 선택 액티비

    private Button btnSelectClub;
    private Button btnGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        String nickName = getIntent().getStringExtra("nickName");
        btnSelectClub = (Button) findViewById(R.id.btnSelectClub);
        btnGuide = (Button) findViewById(R.id.btnGuide);

        btnSelectClub = findViewById(R.id.btnSelectClub);
        btnSelectClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectActivity.this, CategoryActivity.class);
                intent.putExtra("bdh","bdh");
                intent.putExtra("nickName",nickName);
                startActivity(intent);//액티비티 이동
            }
        });

        btnGuide = findViewById(R.id.btnGuide);
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, Guide_Activity.class);
                intent.putExtra("bdh","bdh");
                intent.putExtra("nickName",nickName);
                startActivity(intent);//액티비티 이동
            }
        });


    } // hi
}

//TODO 설렉트에서 동아리 찾기 누르면 카테고리로 넘어가게 , 사용자 설명서 누르면 가이드 액티비티로