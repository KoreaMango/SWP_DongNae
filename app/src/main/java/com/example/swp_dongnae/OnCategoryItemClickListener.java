package com.example.swp_dongnae;

import android.view.View;

public interface OnCategoryItemClickListener { // Category 에 사용됨
    public void onItemClick(CustomAdapter.CustomViewHolder holder, View view, int position); // 인터페이스 메소드 정의
}
