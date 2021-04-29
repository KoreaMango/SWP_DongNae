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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    final ArrayList<CategorySub> arrayList;
    final Context context;


    public CustomAdapter(ArrayList<CategorySub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomAdapter.CustomViewHolder holder = new CustomAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getProfile())
                .into(holder.iv_profile);
        holder.tv_id.setText(arrayList.get(position).getId());
        holder.tv_pw.setText(arrayList.get(position).getPw());
        holder.tv_userName.setText(arrayList.get(position).getUserName());



    }

    @Override
    public int getItemCount() {

        return (arrayList !=null? arrayList.size():0);
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_id;
        TextView tv_pw;
        TextView tv_userName;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_pw = itemView.findViewById(R.id.tv_pw);
            this.tv_userName = itemView.findViewById(R.id.tv_userName);
        }
    }
}
