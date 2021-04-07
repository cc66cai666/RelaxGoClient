package com.cjh.relaxgo.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cjh.relaxgo.R;

import java.util.List;
import java.util.Map;

public class ChildSpikeRecyclerAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Map<String,String>> mapList;

    public ChildSpikeRecyclerAdapter(Context mContext, List<Map<String, String>> mapList) {
        this.mContext = mContext;
        this.mapList = mapList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommodityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.child_commodity_item_style_two,null),mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommodityViewHolder commodityViewHolder = (CommodityViewHolder) holder;
        commodityViewHolder.setData(mapList,position);
    }

    @Override
    public int getItemCount() {
        return mapList.size();
    }

    private class CommodityViewHolder extends RecyclerView.ViewHolder{

        private Context mContext;
        private ImageView cmtImg;
        private TextView price;

        public CommodityViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.mContext = context;
            cmtImg = itemView.findViewById(R.id.iv_commodity);
            price = itemView.findViewById(R.id.tv_commodity_price);
        }

        public void setData(List<Map<String,String>> mapList,int position){

            Map<String, String> map = mapList.get(position);
            String imgs = map.get("imgs");
            String newPrices = map.get("newPrices");
            Glide.with(mContext).load(imgs).into(cmtImg);
            price.setText(newPrices);


        }
    }
}
