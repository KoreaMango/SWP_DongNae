package com.example.swp_dongnae;

import android.view.View;

public interface OnNameItemClickListener { // ClubName 에 사용됨
    public void onItemClick(ClubNameAdapter.ClubNameViewHolder holder, View view, int position); // 인터페이스 메소드 정의
}
