package com.cjh.relaxgo.home.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cjh.relaxgo.R;

import java.util.List;
import java.util.Map;

public class ChildRecommendRecyclerAdapter extends RecyclerView.Adapter {

    private String TAG = "ChildRecommendRecyclerAdapter";
    private Context mContext;
    private List<Map<String, String>> mapList;
    private LayoutInflater layoutInflater;

    public ChildRecommendRecyclerAdapter(Context mContext, List<Map<String, String>> mapList) {
        Log.i(TAG, "ChildRecommendRecyclerAdapter: ");
        this.mContext = mContext;
        this.mapList = mapList;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendViewHolder(layoutInflater.inflate(R.layout.child_commodity_item_style_three, null), mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
        recommendViewHolder.setData(mapList,position);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: "+mapList.size());
        return mapList.size();
    }

    private class RecommendViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ImageView iv_imgRecommend;
        private TextView tvDescription;
        private TextView tvPrice;
        private TextView tvOldPrice;
        private LinearLayout ll_recommend;
        private CardView cv_recommend;


        public RecommendViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            initView(itemView);
        }

        private void initView(View view) {
            iv_imgRecommend = (ImageView) view.findViewById(R.id.iv_imgRecommend);
            tvDescription = (TextView) view.findViewById(R.id.tv_description);
            tvPrice = (TextView) view.findViewById(R.id.tv_price);
            tvOldPrice = (TextView) view.findViewById(R.id.tv_oldPrice);
            cv_recommend = view.findViewById(R.id.cv_recommend);

        }

        public void setData(List<Map<String,String>> mapList,int position) {

            Map<String, String> map = mapList.get(position);
            String imgUrl = map.get("imgUrl");
            String description = map.get("description");
            String price = map.get("price");
            String oldPrice = map.get("oldPrice");
            Log.i(TAG, "setData: "+imgUrl);

            Glide.with(mContext).load(imgUrl).into(iv_imgRecommend);
            tvDescription.setText(description);
            tvPrice.setText(price);
            tvOldPrice.setText(oldPrice);

            cv_recommend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecommendClickListener != null){
                        onRecommendClickListener.RecommendClickListener(position);
                    }
                }
            });


        }

    }

    public interface OnRecommendClickListener{
        public void RecommendClickListener(int position);
    }

    private static OnRecommendClickListener onRecommendClickListener;

    public void setOnRecommendClickListener(OnRecommendClickListener onRecommendClickListener) {
        this.onRecommendClickListener = onRecommendClickListener;
    }
}
