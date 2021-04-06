package com.cjh.relaxgo.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cjh.relaxgo.R;
import com.cjh.relaxgo.home.fragment.ChildHomeFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChildRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> img_url;
    private List<String> img_title;
    private LayoutInflater mLayoutInflater;

    private static final int BANNER = 0;//轮播广告区
    private static final int WELFARE = 1;//福利专区
    private static final int SPIKE = 2;//秒杀专区
    private static final int CHOICE = 3;//精选专区
    private static final int RECOMMEND = 4;//推荐专区

    private int currentType = 0;//当前专区类型

    public ChildRecyclerViewAdapter(Context mContext, List<String> img_url, List<String> img_title) {
        this.mContext = mContext;
        this.img_url = img_url;
        this.img_title = img_title;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.child_fragment_banner, null), mContext);
        }else if (viewType == WELFARE){
            return new WelfareViewHolder(mLayoutInflater.inflate(R.layout.child_welfare_layout,null),mContext);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setData(img_url, img_title);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    /**
     * 得到的专区类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case WELFARE:
                currentType = WELFARE;
                break;
            case SPIKE:
                currentType = SPIKE;
                break;
            case CHOICE:
                currentType = CHOICE;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
        }
        return currentType;
    }

    /**
     * 福利专区内部类
     */
    private class WelfareViewHolder extends RecyclerView.ViewHolder{

        private Context mContext;
        private RecyclerView welfareRecyclerView;

        public WelfareViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.mContext = context;
            String[] imgs = {"https://img.alicdn.com/imgextra/i4/2838892713/O1CN01aE4JKG1VubBzrrTJs_!!2838892713.jpg_430x430q90.jpg",
                                "https://img.alicdn.com/imgextra/i4/4156286767/O1CN01LmG0NU1zrKpjpau2V_!!0-item_pic.jpg_430x430q90.jpg",
                                "https://img.alicdn.com/imgextra/i4/628189716/O1CN01txLBki2LdypLpeANY_!!628189716.jpg_430x430q90.jpg",
                                "https://img.alicdn.com/imgextra/https://img.alicdn.com/imgextra/i2/201749140/O1CN01cyNgxt2HOAdqQSiko_!!201749140.jpg_430x430q90.jpg",
                                "https://img.alicdn.com/imgextra/i2/761832027/O1CN01YYFVzI1QqPRWmOrNm_!!761832027-0-lubanu-s.jpg_430x430q90.jpg"};
            String[] descriptions = {"HUAWEI/华为Mate 40E麒麟旗舰芯片曲面屏华为手机5g手机智能手机华为官方旗舰店mate40e",
                                        "小米11手机 5g手机智能拍照游戏小米10升级骁龙888 官网旗舰店正品",
                                        "百草味华夫饼1kg整箱营养早餐蛋糕食品点心办公室甜点休闲零食",
                                        " 美的智能家用电饭煲4L升电饭锅蛋糕多功能全自动2人正品煮饭E503",
                                        "杜蕾斯避孕套男超薄型0.01安全套子正品旗舰店官方byt正品持久装"};
            String[] newPrices = {"￥4599.00","￥4699.00","￥28.90","￥319.00","￥65.90"};
            String[] oldPrices = {"￥4599.00","￥4699.00","￥59.80","￥399.00","￥89.90"};

            List<Map<String,String>> mapList = new ArrayList<>();
            Map<String,String> map;
            for (int i=0; i<imgs.length; i++){
                map = new HashMap<>();
                map.put("imgs",imgs[i]);
                map.put("descriptions",descriptions[i]);
                map.put("newPrices",newPrices[i]);
                map.put("oldPrices",oldPrices[i]);
                mapList.add(map);
            }
            welfareRecyclerView = itemView.findViewById(R.id.rv_welfare_recyclerView);
            welfareRecyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            welfareRecyclerView.setAdapter(new ChildWelfareRecyclerAdapter(mContext,mapList));
        }
    }

    /**
     * 轮播广告专区ViewHolder内部类
     */
    private class BannerViewHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private Banner banner;
        public BannerViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            banner = itemView.findViewById(R.id.child_home_banner);
        }

        public void setData(List<String> img_url, List<String> img_title) {
            //设置内置样式，共有六种可以点入方法内逐一体验使用。
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片加载器，图片加载器在下方
            banner.setImageLoader(new MyLoader());
            //设置图片网址或地址的集合
            banner.setImages(img_url);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            banner.setBannerAnimation(Transformer.Default);
            //设置轮播图的标题集合
            banner.setBannerTitles(img_title);
            //设置轮播间隔时间
            banner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            banner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            banner.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(listener)
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }

        /**
         * 图片加载器
         */
        private class MyLoader extends ImageLoader {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).into(imageView);
            }
        }

        /**
         * 轮播监听
         */
        private OnBannerListener listener = new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext,"您点击了"+img_title.get(position),Toast.LENGTH_LONG).show();
            }
        };

    }

}
