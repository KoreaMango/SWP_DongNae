package com.example.swp_dongnae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ClubNameAdapter extends RecyclerView.Adapter<ClubNameAdapter.ClubNameViewHolder> {
    final ArrayList<ClubNameSub> arrayList; // 어레이 리스트 변수
    final Context context;
    OnNameItemClickListener listener; // 인터페이스 클래스 변수


    public ClubNameAdapter(ArrayList<ClubNameSub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ClubNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_club_name_adapter, parent, false);
        ClubNameViewHolder holder = new ClubNameViewHolder(view, this.listener); // 뷰홀더 객체 생성
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClubNameViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile()) // 어레이 리스트의 프로필 가져오기
                .into(holder.iv_profile); // 사진 세팅
        holder.tv_id.setText(arrayList.get(position).getId()); // 동아리 이름 세팅


    }

    @Override
    public int getItemCount() {

        return (arrayList != null ? arrayList.size() : 0);
    }

    public void setOnItemClickListener(OnNameItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(ClubNameViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }


    public static class ClubNameViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_profile; // xml 이미지 뷰
        TextView tv_id; // xml 텍스트 뷰

        public ClubNameViewHolder(@NonNull View itemView, final OnNameItemClickListener listener) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile); // 이미지뷰 아이디로 찾기
            this.tv_id = itemView.findViewById(R.id.tv_id); // 텍스트뷰 아이디로 찾기


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ClubNameViewHolder.this, v, position);
                    }

                }
            });

        }

    }

    public ClubNameSub getItem(int position) {
        return arrayList.get(position);
    } // position 가져오는 메소드


}
