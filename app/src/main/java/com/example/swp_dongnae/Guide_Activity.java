package com.example.swp_dongnae;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class Guide_Activity extends AppCompatActivity {
    public static ViewPager viewPager;
    GuideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_slide);
        String nickName = getIntent().getStringExtra("nickName");
        viewPager = findViewById(R.id.viewpager);
        adapter = new GuideViewPagerAdapter(this, nickName);
        viewPager.setAdapter(adapter);
    }
}
