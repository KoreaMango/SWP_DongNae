package com.example.swp_dongnae;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    Button put;
    EditText et_title;


    EditText et_description;

    String title;
    String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = simpleDate.format(mDate);

        String nickName = getIntent().getStringExtra("nickName");
        Log.v("9999", nickName + 1);
        put = findViewById(R.id.putButton);
        et_title = findViewById(R.id.et_noticeTitle);
        TextView et_user = (TextView)findViewById(R.id.et_noticeUser);
        TextView et_date = (TextView)findViewById(R.id.et_noticeDay);
        et_date.setText(getTime);
        et_user.setText(nickName);

        et_description = findViewById(R.id.et_noticeDes);


        //무슨 코드인지 몰라서 주석처리합니다
        //FirebaseMessaging.getInstance().subscribeToopic("notice");

        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = et_title.getText().toString();
                description = et_description.getText().toString();

                String flag;
                flag = getIntent().getStringExtra("flag");
                Intent intentB = new Intent();

                if (!title.isEmpty() && !getTime.isEmpty() && !nickName.isEmpty() && !description.isEmpty()) {
                    if (flag.equals("work")) {
                        intentB.putExtra("work1", title);
                        intentB.putExtra("work2", getTime);
                        intentB.putExtra("work3", nickName);
                        intentB.putExtra("work4", description);
                        Log.v("9999", "월크로가자");
                        setResult(RESULT_OK, intentB);
                        finish();


                    } else if (flag.equals("qna")) {
                        intentB.putExtra("qna1", title);
                        intentB.putExtra("qna2", getTime);
                        intentB.putExtra("qna3", nickName);
                        intentB.putExtra("qna4", description);
                        Log.v("9999", "qna로 가자 ");
                        setResult(RESULT_OK, intentB);
                        finish();
                    }
                } else {
                    AlertDialog.Builder ad = new AlertDialog.Builder(WriteActivity.this);
                    ad.setTitle("글쓰기 실패");
                    ad.setMessage("작성하지 않은 항목이 있습니다.");
                    ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();

                }

            }
        });
    }
}