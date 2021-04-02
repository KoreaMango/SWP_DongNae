package com.example.swp_dongnae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        super.onDestroy();
    }
}