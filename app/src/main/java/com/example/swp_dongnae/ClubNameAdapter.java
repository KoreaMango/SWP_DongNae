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

public class ClubNameAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    final ArrayList<ClubNameSub> arrayList;
    final Context context;


    public ClubNameAdapter(ArrayList<ClubNameSub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_club_name_adapter,parent,false);
        CustomAdapter.CustomViewHolder holder = new CustomAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
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


    //public interface TODO interface
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        // 데이터 리스트로부터 아이템 데이터 참조.
                        ClubNameSub item = arrayList.get(pos);

                        // TODO : use item.
                    }
                }
            });
        }
    }
}
