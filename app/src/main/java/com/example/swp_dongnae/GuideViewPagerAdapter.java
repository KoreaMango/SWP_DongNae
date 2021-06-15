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
    Context ctx; //출현할 페이지(context)
    String nickName; //임의의 문자열


    public GuideViewPagerAdapter(Context ctx, String nickName) {
        this.ctx = ctx;
        this.nickName = nickName;
    }

    @Override
    public int getCount() { //이 뷰가 몇개의 페이지를 가지고 있는지 알려줌.

        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) { //해당 view와 object가 동일 한지 여부 검사와 결과 값 출력
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) { //View에 넣을 Page 객체를 리턴해주는 작업
        LayoutInflater layoutInflater= (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_screen,container,false);


        ImageView logo=view.findViewById(R.id.logo); //이미지뷰 주소
        ImageView ind1=view.findViewById(R.id.ind1);
        ImageView ind2=view.findViewById(R.id.ind2);
        ImageView ind3=view.findViewById(R.id.ind3);

        TextView Title=view.findViewById(R.id.Title);//텍스트뷰 주소
        TextView desc=view.findViewById(R.id.desc);

        ImageView next=view.findViewById(R.id.next);
        ImageView back=view.findViewById(R.id.back);
        Button btnGetStarted=view.findViewById(R.id.btnGetStarted);//버튼값 주소
        btnGetStarted.setOnClickListener(new View.OnClickListener() { //버튼클릭시
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ctx,SelectActivity.class);
                intent.putExtra("bdh","bdh");
                intent.putExtra("nickName",nickName);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);

            }
        });


        next.setOnClickListener(new View.OnClickListener() { //next 버튼 클릭시
            @Override
            public void onClick(View v) {
                Guide_Activity.viewPager.setCurrentItem(position+1); //현재페이지에서 다음 페이지로 +1만큼 이동

            }
        });
        back.setOnClickListener(new View.OnClickListener() { //back버튼 클릭시
            @Override
            public void onClick(View v) {
                Guide_Activity.viewPager.setCurrentItem(position-1); } //현재페이지에서 다음 페이지로 -1만큼 이동
        });
        switch (position) //스와이프시 페이지 전환
        {
            case 0: //1페이지
                logo.setImageResource(R.drawable.logo);
                ind1.setImageResource(R.drawable.selected);
                ind2.setImageResource(R.drawable.unselected);
                ind3.setImageResource(R.drawable.unselected);

                Title.setText("동네");
                desc.setText("동네는 동아리 네트워크를 줄인 말로 동아리를 찾고자 하는 학생 , 동아리회원을 모집하고자 하는 모든 학생들에게 도움을 주고자 만든 어플입니다.");
                back.setVisibility(View.GONE);
                next.setVisibility(View.VISIBLE);
                break;

            case 1: //2페이지
                logo.setImageResource(R.drawable.human);
                ind1.setImageResource(R.drawable.unselected);
                ind2.setImageResource(R.drawable.selected);
                ind3.setImageResource(R.drawable.unselected);

                Title.setText("일반회원");
                desc.setText("일반 회원은 자신이 원하는 동아리의 분과와 해당 동아리를 선택하여 궁금한점 ,활동 등 다양한 정보를 얻을수 있습니다. ");
                back.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                break;

            case 2: //3페이지
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


        container.addView(view); //메인에 뷰를 추가
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) { //뷰를 삭제한다.
        container.removeView((View) object);
    }
}
