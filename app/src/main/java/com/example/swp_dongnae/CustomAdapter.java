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
    final ArrayList<CategorySub> arrayList; // 어레이 리스트 변수
    final Context context;
    OnCategoryItemClickListener listener;// 인터페이스 클래스 변수


    public CustomAdapter(ArrayList<CategorySub> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // 뷰 홀더와 레이아웃 파일을 연결해주는 역할
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        CustomAdapter.CustomViewHolder holder = new CustomAdapter.CustomViewHolder(view,this.listener);// 뷰홀더 객체 생성
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) { // 함수는 생성된 뷰홀더에 데이터를 바인딩 해주는 함수이다.
        holder.tv_id.setText(arrayList.get(position).getId());


    }

    @Override
    public int getItemCount() { // 행 개수를 리턴

        return (arrayList !=null? arrayList.size():0);
    }
    public void setOnItemClickListener(OnCategoryItemClickListener listener) { //행의 이벤트 클릭을 받아들임
        this.listener = listener;
    }
    public void onItemClick(CustomViewHolder holder, View view, int position) { //뷰의 한 항목이 클릭 되었을때, 콜백되어 호출 된다.
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }
    public static class CustomViewHolder extends RecyclerView.ViewHolder { //커스텀 뷰홀더 클래스 생성
        TextView tv_id;//xml 텍스트 뷰

        public CustomViewHolder(@NonNull View itemView, final OnCategoryItemClickListener listener) {
            super(itemView);
            this.tv_id = itemView.findViewById(R.id.tv_id); // 텍스트뷰 아이디로 찾기

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {  //클릭시 어뎁터에서 정해진 순서를 받아온다.
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

    public CategorySub getItem(int position) { return arrayList.get(position); }// position 가져오는 메소드

//    public void setItem(int position, Person item) {
//        items.set(position, item);
//    }


}
