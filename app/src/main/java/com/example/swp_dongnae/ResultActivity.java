package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {
    private Button btn_continue;
    private TextView tv_result;
    private ImageView iv_profile;
   

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent =getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUrl = intent.getStringExtra("photoUrl");

        tv_result= findViewById(R.id.tv_result);
        tv_result.setText(nickName);

        iv_profile= findViewById(R.id.iv_profile);
        Glide.with(this).load(photoUrl).into(iv_profile);


        btn_continue = findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,SelectActivity.class);
                startActivity(intent);//액티비티 이동

            }
        });


    }
}