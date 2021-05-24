package com.example.swp_dongnae;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WriteActivity extends AppCompatActivity {

    Button put;
    EditText et_title;
    EditText et_date;
    EditText et_user;
    EditText et_description;

    String title;
    String date;
    String user;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        String nickName = getIntent().getStringExtra("nickName");
        put = findViewById(R.id.putButton);
        et_title = findViewById(R.id.et_noticeTitle);
        et_date = findViewById(R.id.et_noticeDay);
        TextView et_user = (TextView)findViewById(R.id.et_noticeUser);
        et_user.setText(nickName);
        et_description = findViewById(R.id.et_noticeDes);




        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_title.getText().toString();
                date = et_date.getText().toString();
                description = et_description.getText().toString();

                Log.v("9999",title + date + user + description);
                String flag;
                flag = getIntent().getStringExtra("flag");
                Intent intentB = new Intent();

                if (!title.isEmpty()) {
                    if (flag.equals("work")) {
                        intentB.putExtra("work1", title);
                        intentB.putExtra("work2", date);
                        intentB.putExtra("work3", nickName);
                        intentB.putExtra("work4", description);
                        Log.v("9999", "월크로가자");


                    } else if (flag.equals("qna")) {
                        intentB.putExtra("qna1", title);
                        intentB.putExtra("qna2", date);
                        intentB.putExtra("qna3", user);
                        intentB.putExtra("qna4", description);
                        Log.v("9999", "qna로 가자 ");
                    }
                } else {
                    if (flag.equals("work")) {
                        intentB.putExtra("work1", "fail");
                    } else if (flag.equals("qna")) {
                        intentB.putExtra("qna1", "fail");
                    }
                    Log.v("9999", "페일값 ");

                }
                setResult(RESULT_OK, intentB);
                finish();
            }
        });
    }
}