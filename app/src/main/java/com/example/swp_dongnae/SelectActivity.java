package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectActivity extends AppCompatActivity { // 동아리 찾기 or 앱 사용 설명서 선택 액티비

    private Button btnSelectClub; //동아리 찾기 버튼
    private Button btnGuide; //사용설명서 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) { //레이아웃을 생성하고 초기화 컴포넌트를 가져옴
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        String nickName = getIntent().getStringExtra("nickName");
        btnSelectClub = (Button) findViewById(R.id.btnSelectClub);
        btnGuide = (Button) findViewById(R.id.btnGuide);

        btnSelectClub = findViewById(R.id.btnSelectClub);
        btnSelectClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //버튼 클릭시 해당 페이지로 전환

                Intent intent = new Intent(SelectActivity.this, CategoryActivity.class); //선택페이지에서 카테고리 페이지로
                intent.putExtra("bdh","bdh");
                intent.putExtra("nickName",nickName);
                startActivity(intent);//액티비티 이동
            }
        });

        btnGuide = findViewById(R.id.btnGuide);
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this, Guide_Activity.class); //선택페이지에서 사용자설명 페이지로
                intent.putExtra("bdh","bdh");
                intent.putExtra("nickName",nickName);
                startActivity(intent);//액티비티 이동
            }
        });


    }
}

//TODO 설렉트에서 동아리 찾기 누르면 카테고리로 넘어가게 , 사용자 설명서 누르면 가이드 액티비티로