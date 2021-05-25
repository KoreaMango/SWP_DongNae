package com.example.swp_dongnae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading extends Activity {

    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        startLoading();
    }
    private void startLoading() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Loading.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }


}
