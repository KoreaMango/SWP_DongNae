package com.example.swp_dongnae;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

//동아리 페이지를 뷰페이저로 구현
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

        //동아리 카테고리 페이지에서 값 가져온다.
        String clubName = getIntent().getStringExtra("club");

        tv_clubName = findViewById(R.id.tv_clubName);
        tv_clubName.setText(clubName);

    }


}