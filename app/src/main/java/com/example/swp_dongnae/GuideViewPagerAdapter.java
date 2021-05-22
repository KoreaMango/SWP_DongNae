package com.example.swp_dongnae;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class GuideViewPagerAdapter extends PagerAdapter {
    Context ctx;


    public GuideViewPagerAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {

        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_screen,container,false);


        ImageView logo=view.findViewById(R.id.logo);
        ImageView ind1=view.findViewById(R.id.ind1);
        ImageView ind2=view.findViewById(R.id.ind2);
        ImageView ind3=view.findViewById(R.id.ind3);

        TextView Title=view.findViewById(R.id.Title);
        TextView desc=view.findViewById(R.id.desc);

        ImageView next=view.findViewById(R.id.next);
        ImageView back=view.findViewById(R.id.back);
        Button btnGetStarted=view.findViewById(R.id.btnGetStarted);
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,SelectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);

            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guide_Activity.viewPager.setCurrentItem(position+1);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guide_Activity.viewPager.setCurrentItem(position-1);
            }
        });
        switch (position)
        {
            case 0:
                logo.setImageResource(R.drawable.logo);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                Title.setText("동네");
                desc.setText("동네는 동아리를 찾고자 하는 학생 , 동아리회원을 모집하고자 하는 모든 학생들에게 도움을 주고자 만든 어플입니다.");
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                break;

            case 1:
                logo.setImageResource(R.drawable.human);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                Title.setText("일반회원");
                desc.setText("일반 회원은 동아리 찾기를 이용하여 자신이 원하는 동아리의 정보를 얻을수 있습니다.");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 2:
                logo.setImageResource(R.drawable.businessman);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.selected);

                Title.setText("회장 회원");
                desc.setText("회장 회원님은 어플 초기 화면의 문의하기 버튼을 이용하여 개발자에게 자신의 동아리에 대한 정보를 제공해주시면 동아리 홍보 및 정보제공을 대신해드립니다.");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                break;


        }


        container.addView(view);
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
