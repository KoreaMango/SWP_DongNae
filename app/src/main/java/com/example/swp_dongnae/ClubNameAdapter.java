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
    final ArrayList<ClubNameSub> arrayList;
    final Context context;
    OnNameItemClickListener listener;


    public ClubNameAdapter(ArrayList<ClubNameSub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ClubNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_club_name_adapter, parent, false);
        ClubNameViewHolder holder = new ClubNameViewHolder(view, this.listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClubNameViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_pw.setText(arrayList.get(position).getPw());
        holder.tv_userName.setText(arrayList.get(position).getUserName());

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

        ImageView iv_profile;
        TextView tv_id;
        TextView tv_pw;
        TextView tv_userName;

        public ClubNameViewHolder(@NonNull View itemView, final OnNameItemClickListener listener) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_pw = itemView.findViewById(R.id.tv_pw);
            this.tv_userName = itemView.findViewById(R.id.tv_userName);

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

//        public void setItem(Person item) {
//            nameText.setText(item.getName());
//            mobileText.setText(item.getMobile());
//        }

    }



//    public void addItem(Person item) {
//        items.add(item);
//    }
//
//    public void setItems(ArrayList<Person> items) {
//        this.items = items;
//    }

    public ClubNameSub getItem(int position) {
        return arrayList.get(position);
    }

//    public void setItem(int position, Person item) {
//        items.set(position, item);
//    }


}
