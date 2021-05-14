package com.example.swp_dongnae;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class RepleListAdapter extends BaseAdapter {

    private Context context;
    private List<Reple> repleList;

    public RepleListAdapter(Context context, List<Reple> repleList) {
        this.context = context;
        this.repleList = repleList;
    }

    @Override
    public int getCount() {
        return repleList.size();
    }

    @Override
    public Object getItem(int position) {
        return repleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.activity_reple_list_adapter,null);

        TextView repleUser = (TextView) v.findViewById(R.id.repleUser);
        TextView repleDescription = (TextView) v.findViewById(R.id.repleDescription);


        repleUser.setText(repleList.get(position).getUser());
        repleDescription.setText(repleList.get(position).getDescription());

        v.setTag(repleList.get(position).getUser());
        return v;

    }
}