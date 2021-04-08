package com.example.swp_dongnae;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this,"38294660a3a4b4dda7d5a032233d78cb");
    }
}
