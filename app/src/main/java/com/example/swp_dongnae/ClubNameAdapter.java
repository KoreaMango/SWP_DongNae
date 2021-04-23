package com.example.swp_dongnae;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClubNameAdapter extends BaseAdapter {

    private Context context;
    private List<ClubNameSub> clubNameSubList;

    public ClubNameAdapter(Context context, List<ClubNameSub> clubNameSubList){
        this.context = context;
        this.clubNameSubList = clubNameSubList;
    }


    @Override
    public int getCount() { return clubNameSubList.size();}

    @Override
    public Object getItem(int i) { return clubNameSubList.get(i);}

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.activity_club_name_adapter,null);

        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        TextView textView = (TextView) v.findViewById(R.id.textView);

        textView.setText(clubNameSubList.get(i).getTextView());
        //imageView.(clubNameSubList.get(i).getImageView()); TODO 파이어 베이스에 이미지 값 넣기

        v.setTag(clubNameSubList.get(i).getTextView());
        return v;
    }
}