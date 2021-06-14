package com.example.swp_dongnae;

import android.view.View;

public interface OnNoticeItemClickListener { // Notice 에 사용됨
    public void onItemClick(NoticeAdapter.NoticeViewHolder holder, View view, int position); // 인터페이스 메소드 정의
}
