package com.cjh.relaxgo.personal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjh.relaxgo.R;

public class MyGridViewAdapter extends BaseAdapter {

    private int[] icons;
    private int[] titles;
    private Context context;

    public MyGridViewAdapter(Context context,int[] icon,int[] title) {
        this.context = context;
        this.icons = icon;
        this.titles = title;

    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        GridViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.personal_grid_item, null);
            holder = new GridViewHolder();
            holder.imageView = view.findViewById(R.id.grid_item_icon);
            holder.textView = view.findViewById(R.id.grid_item_title);
            view.setTag(holder);
        }else {
            holder = (GridViewHolder) view.getTag();
        }
        holder.imageView.setImageResource(icons[i]);
        holder.textView.setText(titles[i]);

        return view;
    }

    class GridViewHolder {

        private ImageView imageView;
        private TextView textView;

    }
}
