package com.example.swp_dongnae;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BsAdapter extends RecyclerView.Adapter<BsAdapter.CustomViewHolder> {
    private ArrayList<Bs> arrayList;
    private Context context;


    public BsAdapter(ArrayList<Bs> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bs, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tv_act.setText(arrayList.get(position).getActivity());
        holder.tv_pur.setText(arrayList.get(position).getPurpose());
        holder.tv_cap.setText(arrayList.get(position).getCaptain());
        holder.tv_cat.setText(arrayList.get(position).getCategory());
        holder.tv_tel.setText(arrayList.get(position).getTel());
        holder.tv_email.setText(arrayList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return (arrayList !=null? arrayList.size():0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_act;
        TextView tv_pur;
        TextView tv_cap;
        TextView tv_cat;
        TextView tv_tel;
        TextView tv_email;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_act = itemView.findViewById(R.id.tv_act);
            this.tv_pur = itemView.findViewById(R.id.tv_pur);
            this.tv_cap = itemView.findViewById(R.id.tv_cap);
            this.tv_cat = itemView.findViewById(R.id.tv_cat);
            this.tv_tel = itemView.findViewById(R.id.tv_tel);
            this.tv_email = itemView.findViewById(R.id.tv_email);
        }
    }
}
