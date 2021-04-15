package com.cjh.relaxgo.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cjh.relaxgo.R;

import java.util.List;
import java.util.Map;

public class ChildWelfareRecyclerAdapter extends RecyclerView.Adapter {

    private String TAG = "ChildWelfareRecyclerAdapter";

    private Context mContext;
    private List<Map<String, String>> commodities;
    private LayoutInflater layoutInflater;

    public ChildWelfareRecyclerAdapter(Context mContext, List<Map<String, String>> commodities) {
        this.mContext = mContext;
        this.commodities = commodities;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommodityViewHolder(layoutInflater.inflate(R.layout.child_commodity_item_style_one, null),mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommodityViewHolder commodityViewHolder = (CommodityViewHolder) holder;
        commodityViewHolder.setData(commodities,position);
    }

    @Override
    public int getItemCount() {
        return commodities.size();
    }

    private class CommodityViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ImageView ivCommodityImg;
        private TextView tvCommodityDescription;
        private TextView tvPriceNow;
        private TextView tvPriceOld;
        private LinearLayout commodityOne;

        public CommodityViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            initView(itemView);
        }

        private void initView(View view) {
            ivCommodityImg = (ImageView) view.findViewById(R.id.iv_commodity_img);
            tvCommodityDescription = (TextView) view.findViewById(R.id.tv_commodity_description);
            tvPriceNow = (TextView) view.findViewById(R.id.tv_price_now);
            tvPriceOld = (TextView) view.findViewById(R.id.tv_price_old);
            commodityOne = view.findViewById(R.id.ll_commodity_item_one);
        }

        public void setData(List<Map<String,String>> mapList,int position) {
            Map<String, String> map = mapList.get(position);
            Log.i(TAG, "setData: ");
            String img = map.get("imgs");
            String descriptions = map.get("descriptions");
            String newPrices = map.get("newPrices");
            String oldPrices = map.get("oldPrices");
            Glide.with(mContext).load(img).into(ivCommodityImg);

            tvCommodityDescription.setText(descriptions);
            tvPriceNow.setText(newPrices);
            tvPriceOld.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            tvPriceOld.setText(oldPrices);

            commodityOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onWelfareClickListener != null){
                        onWelfareClickListener.ClickListener(position);
                    }
                }
            });

        }
    }

    public interface OnWelfareClickListener{
        public void ClickListener(int position);
    }

    private static OnWelfareClickListener onWelfareClickListener;

    public void setOnWelfareClickListener(OnWelfareClickListener onWelfareClickListener) {
        this.onWelfareClickListener = onWelfareClickListener;
    }
}
