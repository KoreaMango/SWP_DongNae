package com.example.swp_dongnae;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Guide_Activity extends AppCompatActivity {
    public static ViewPager viewPager;
    GuideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //레이아웃을 생성하고 초기화 컴포넌트를 가져옴
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_slide); //사용자 설명 레이아웃 배치
        String nickName = getIntent().getStringExtra("nickName");
        viewPager = findViewById(R.id.viewpager); //어뎁터 객체 선언
        adapter = new GuideViewPagerAdapter(this, nickName);//페이지 어뎁터를 상속받아 구현
        viewPager.setAdapter(adapter); //뷰페이저와 어뎁터 연동
    }
}
