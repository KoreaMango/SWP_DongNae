package com.example.swp_dongnae;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    //뷰페이저에서의 프래그먼트 화면 전환 처리 구현
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Info.newinstance();
            case 1:
                return Activity.newinstance();
            case 2:
                return Work.newinstance();
            case 3:
                return QnA.newinstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {

        return 4;

    }

    // 상단의 탭 레이아웃 인디케이터 통해 텍스트 선언해주는 곳
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "정보";
            case 1:
                return "활동";
            case 2:
                return "협업";
            case 3:
                return "QnA";
            default:
                return null;
        }
    }
}
