package com.example.swp_dongnae;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    // 데이터 리스트로부터 아이템 데이터 참조.
                    //RecyclerItem item = mData.get(pos);

                    // TODO : use item.
                }
            }
        });
    }
}
