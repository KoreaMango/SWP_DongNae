package com.example.swp_dongnae;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    final ArrayList<NoticeSub> arrayList;
    final Context context;
    OnNoticeItemClickListener listener;


    public NoticeAdapter(ArrayList<NoticeSub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notice_adapter, parent, false);
        NoticeViewHolder holder = new NoticeViewHolder(view, this.listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {

        Log.v("013",arrayList.get(position).getDes());
        holder.des.setText(arrayList.get(position).getDes());
        Log.v("013",arrayList.get(position).getUser());
        holder.user.setText(arrayList.get(position).getUser());
        Log.v("013",arrayList.get(position).getDate());
        holder.date.setText(arrayList.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }

    public void setOnItemClickListener(OnNoticeItemClickListener listener) {
        this.listener = listener;
    }

    public void onItemClick(NoticeViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }


    public static class NoticeViewHolder extends RecyclerView.ViewHolder {

        TextView des;
        TextView user;
        TextView date;

        public NoticeViewHolder(@NonNull View itemView, final OnNoticeItemClickListener listener) {
            super(itemView);
            this.des = itemView.findViewById(R.id.tv_des);
            this.user = itemView.findViewById(R.id.tv_user);
            this.date = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(NoticeViewHolder.this, v, position);
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

    public NoticeSub getItem(int position) {
        return arrayList.get(position);
    }

//    public void setItem(int position, Person item) {
//        items.set(position, item);
//    }


}
