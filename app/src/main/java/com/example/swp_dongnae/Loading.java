package com.example.swp_dongnae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading extends Activity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        startLoading();
    }
    //로딩화면 시작
    private void startLoading() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Loading.this, MainActivity.class); // 로딩화면 전환
                startActivity(intent); // 화면 시작
                finish();
            }
        }, 3000); //3초간 화면 보여준다.
    }


}
