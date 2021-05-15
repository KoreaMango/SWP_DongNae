package com.example.swp_dongnae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    final ArrayList<CategorySub> arrayList;
    final Context context;
    OnCategoryItemClickListener listener;


    public CustomAdapter(ArrayList<CategorySub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomAdapter.CustomViewHolder holder = new CustomAdapter.CustomViewHolder(view,this.listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_id.setText(arrayList.get(position).getId());


    }

    @Override
    public int getItemCount() {

        return (arrayList !=null? arrayList.size():0);
    }
    public void setOnItemClickListener(OnCategoryItemClickListener listener) {
        this.listener = listener;
    }
    public void onItemClick(CustomViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id;

        public CustomViewHolder(@NonNull View itemView, final OnCategoryItemClickListener listener) {
            super(itemView);
            this.tv_id = itemView.findViewById(R.id.tv_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(CustomViewHolder.this, v, position);
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

    public CategorySub getItem(int position) {
        return arrayList.get(position);
    }

//    public void setItem(int position, Person item) {
//        items.set(position, item);
//    }


}
