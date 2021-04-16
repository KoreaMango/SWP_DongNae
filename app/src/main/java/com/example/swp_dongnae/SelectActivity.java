package com.example.swp_dongnae;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectActivity extends AppCompatActivity { // 동아리 찾기 or 앱 사용 설명서 선택 액티비

    private Button btnSelectClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        btnSelectClub = (Button)findViewById(R.id.btnSelectClub);
        Button btnGuide = (Button) findViewById(R.id.btnGuide);

        btnSelectClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}