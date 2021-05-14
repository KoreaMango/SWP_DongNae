package com.example.swp_dongnae;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ViewClub extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;

    TextView tv_clubName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bs);

        //뷰페이저 세팅
        ViewPager viewPager = findViewById(R.id.vp);
        fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        String pos = getIntent().getStringExtra("club");

        Log.v("010773682",pos+"1");
        tv_clubName = findViewById(R.id.tv_clubName);
        tv_clubName.setText(pos);

    }
}