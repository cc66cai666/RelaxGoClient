package com.cjh.relaxgo.home.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.cjh.relaxgo.R;
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
        } else if (viewType == WELFARE) {
            return new WelfareViewHolder(mLayoutInflater.inflate(R.layout.child_welfare_layout, null), mContext);
        } else if (viewType == SPIKE) {
            return new SpikeViewHolder(mLayoutInflater.inflate(R.layout.child_spike_layout, null), mContext);
        } else if (viewType == CHOICE) {
            return new ChoiceViewHolder(mLayoutInflater.inflate(R.layout.child_choice_layout, null), mContext);
        } else if (viewType == RECOMMEND) {
            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.child_recommend_layout,null),mContext);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == BANNER) {
            BannerViewHolder bannerHolder = (BannerViewHolder) holder;
            bannerHolder.setData(img_url, img_title);
        } else if (getItemViewType(position) == WELFARE) {
            WelfareViewHolder welfareViewHolder = (WelfareViewHolder) holder;
            welfareViewHolder.setData();
        } else if (getItemViewType(position) == SPIKE) {
            SpikeViewHolder spikeViewHolder = (SpikeViewHolder) holder;
            spikeViewHolder.setData();
        } else if (getItemViewType(position) == CHOICE) {
            ChoiceViewHolder choiceViewHolder = (ChoiceViewHolder) holder;
            choiceViewHolder.setData();
        } else if (getItemViewType(position) == RECOMMEND) {
            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
            recommendViewHolder.setData();
        }

    }

    @Override
    public int getItemCount() {
        return 5;
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


    private class RecommendViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private RecyclerView rvRecommend;

        public RecommendViewHolder(@NonNull View itemView,Context context) {
            super(itemView);
            this.mContext = context;
            rvRecommend = itemView.findViewById(R.id.rv_recommend);
        }

        public void setData() {
            String[] imgUrls = {"http://img14.360buyimg.com/n1/s350x449_jfs/t1/166624/19/15700/151425/605994f0E6873fc33/d159a72df43143b1.jpg!cc_350x449.jpg",
                    "http://img11.360buyimg.com/ceco/s600x600_jfs/t1/154195/37/18775/65653/603463b2E1f70791f/05d8ad979c24be2f.jpg!q70.jpg",
                    "http://img14.360buyimg.com/n1/s350x449_jfs/t1/168408/24/14973/284795/605df47fE9a3d225f/f2c13b02dcc6e5eb.jpg!cc_350x449.jpg",
                    "http://img13.360buyimg.com/n1/jfs/t1/132093/20/13354/34370/5f924b27Edadd4b1f/f1ce078447575167.jpg",
                    "http://img12.360buyimg.com/n1/s350x449_jfs/t1/159819/10/14300/142091/605407fcE2444e22d/3c63b46bd2473f2f.jpg!cc_350x449.jpg",
                    "http://img10.360buyimg.com/n1/jfs/t1/107928/25/3043/196739/5e0d5a5cEaf1dbc6e/9557e2b8129addc4.jpg",
                    "http://img13.360buyimg.com/n1/jfs/t1/132093/20/13354/34370/5f924b27Edadd4b1f/f1ce078447575167.jpg",
                    "http://img12.360buyimg.com/n1/s350x449_jfs/t1/159819/10/14300/142091/605407fcE2444e22d/3c63b46bd2473f2f.jpg!cc_350x449.jpg",
                    "http://img10.360buyimg.com/n1/jfs/t1/107928/25/3043/196739/5e0d5a5cEaf1dbc6e/9557e2b8129addc4.jpg",
                    "https://gd4.alicdn.com/imgextra/i1/4040701594/O1CN01x14cHT1Ne5zYofdRm_!!4040701594.jpg_400x400.jpg",
                    "http://img12.360buyimg.com/n1/s450x450_jfs/t1/174949/34/1132/126466/6062f299E45fd6334/941d7b48aca9f901.jpg",
                    "http://img13.360buyimg.com/n1/jfs/t1/132093/20/13354/34370/5f924b27Edadd4b1f/f1ce078447575167.jpg",
                    "http://img10.360buyimg.com/n1/jfs/t1/107928/25/3043/196739/5e0d5a5cEaf1dbc6e/9557e2b8129addc4.jpg",
                    "http://img13.360buyimg.com/n1/jfs/t1/104027/27/18094/95944/5e901ad7Ed2ce7f82/8ed74eaf42e5b7ff.jpg"};
            String[] descriptions = {"HUAWEI/华为Mate 40E麒麟旗舰芯片曲面屏华为手机5g手机智能手机华为官方旗舰店mate40e",
                    "小米11手机 5g手机智能拍照游戏小米10升级骁龙888 官网旗舰店正品",
                    "百草味华夫饼1kg整箱营养早餐蛋糕食品点心办公室甜点休闲零食",
                    " 美的智能家用电饭煲4L升电饭锅蛋糕多功能全自动2人正品煮饭E503",
                    "杜蕾斯避孕套男超薄型0.01安全套子正品旗舰店官方byt正品持久装",
                    "百草味华夫饼1kg整箱营养早餐蛋糕食品点心办公室甜点休闲零食",
                    " 美的智能家用电饭煲4L升电饭锅蛋糕多功能全自动2人正品煮饭E503",
                    "杜蕾斯避孕套男超薄型0.01安全套子正品旗舰店官方byt正品持久装",
                    "百草味华夫饼1kg整箱营养早餐蛋糕食品点心办公室甜点休闲零食",
                    "百草味华夫饼1kg整箱营养早餐蛋糕食品点心办公室甜点休闲零食",
                    " 美的智能家用电饭煲4L升电饭锅蛋糕多功能全自动2人正品煮饭E503",
                    "杜蕾斯避孕套男超薄型0.01安全套子正品旗舰店官方byt正品持久装",
                    "百草味华夫饼1kg整箱营养早餐蛋糕食品点心办公室甜点休闲零食",
                    " 美的智能家用电饭煲4L升电饭锅蛋糕多功能全自动2人正品煮饭E503",};
            String[] newPrices = {"￥4599.00", "￥4699.00", "￥28.90", "￥319.00", "￥65.90", "￥65.90", "￥319.00", "￥65.90", "￥65.90", "￥4699.00", "￥28.90", "￥319.00", "￥65.90", "￥65.90"};
            String[] oldPrices = {"￥4599.00", "￥4699.00", "￥59.80", "￥399.00", "￥89.90","￥89.90", "￥399.00", "￥89.90","￥89.90","￥399.00", "￥89.90","￥89.90", "￥399.00", "￥89.90"};

            List<Map<String,String>> mapList = new ArrayList<>();
            Map<String,String> map;
            for (int i=0; i<imgUrls.length; i++){
                map = new HashMap<>();
                map.put("imgUrl",imgUrls[i]);
                map.put("description",descriptions[i]);
                map.put("price",newPrices[i]);
                map.put("oldPrice",oldPrices[i]);
                mapList.add(map);
            }

            rvRecommend.setAdapter(new ChildRecommendRecyclerAdapter(mContext,mapList));
            rvRecommend.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            SpacesItemDecoration itemDecoration = new SpacesItemDecoration(35);
            rvRecommend.addItemDecoration(itemDecoration);

        }

        public class SpacesItemDecoration extends RecyclerView.ItemDecoration{

            private int space;

            public SpacesItemDecoration(int space) {
                this.space = space;
            }

            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = space;
                outRect.bottom = space;
            }
        }

    }

    /**
     * 精选专区内部类
     */
    private class ChoiceViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private Banner bannerImg;
        private TextView tvTitle4;
        private TextView tvTitleChild4;
        private ImageView ivLeft4;
        private ImageView ivRight4;
        private TextView tvTitle1;
        private TextView tvTitleChild1;
        private ImageView ivLeft1;
        private ImageView ivRight1;
        private TextView tvTitle2;
        private TextView tvTitleChild2;
        private ImageView ivLeft2;
        private ImageView ivRight2;
        private TextView tvTitle3;
        private TextView tvTitleChild3;
        private ImageView ivLeft3;
        private ImageView ivRight3;

        public ChoiceViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            initView(itemView);
        }

        public void setData() {

            setBanner();
            setItem1();


        }

        private void setItem1() {
            tvTitle1.setText("运动");
            tvTitleChild1.setText("品质好物");
            Glide.with(mContext).load("http://img10.360buyimg.com/n1/jfs/t1/159036/40/688/135205/5fec4a2eEa6f94aee/d445fa3e41d5b65c.jpg")
                    .into(ivLeft1);
            Glide.with(mContext).load("http://img10.360buyimg.com/n1/jfs/t1/131996/18/2634/279712/5eedadd5Efb6c86e3/293e9f77e9b30dba.jpg")
                    .into(ivRight1);

            tvTitle2.setText("厨具");
            tvTitleChild2.setText("买它买它");
            Glide.with(mContext).load("http://img10.360buyimg.com/n1/jfs/t26143/31/2571160644/63356/2058b99c/5c052113N34094dd5.jpg")
                    .into(ivLeft2);
            Glide.with(mContext).load("http://img10.360buyimg.com/n1/jfs/t20368/339/706160702/349192/908a609/5b163159Ncaa95466.jpg")
                    .into(ivRight2);

            tvTitle3.setText("数码");
            tvTitleChild3.setText("性价比高");
            Glide.with(mContext).load("http://img14.360buyimg.com/n1/s450x450_jfs/t1/169390/28/3754/280252/600a69deE191d4b26/57b6b8872d39a0fa.jpg")
                    .into(ivLeft3);
            Glide.with(mContext).load("http://img11.360buyimg.com/n1/s450x450_jfs/t1/117876/6/17129/49236/5f53595fEf9e5c9ab/c37ac9869dd520da.jpg")
                    .into(ivRight3);

            tvTitle4.setText("服饰");
            tvTitleChild4.setText("又美又帅");
            Glide.with(mContext).load("http://img10.360buyimg.com/n1/s350x449_jfs/t1/101367/21/13316/143320/5e5736f7E97ee248e/75050f632628c91e.jpg!cc_350x449.jpg")
                    .into(ivLeft4);
            Glide.with(mContext).load("http://img14.360buyimg.com/n1/s350x449_jfs/t1/161205/17/15051/411520/605df47bEa34ef321/9303f7ea6ea63bed.jpg!cc_350x449.jpg")
                    .into(ivRight4);

        }

        private void setBanner() {
            List<String> img_url = new ArrayList<>();
            img_url.add("http://img14.360buyimg.com/ceco/s600x600_jfs/t1/95968/36/13935/185871/5e60e8e4E046c2490/47dcb4e6ad6c9f36.jpg!q70.jpg");
            img_url.add("http://img10.360buyimg.com/mobilecms/s600x600_jfs/t1/85861/12/1062/265787/5db85a80E9439c1f8/06edb8d7690a2baf.png");
            img_url.add("http://img11.360buyimg.com/ceco/s600x600_jfs/t1/154195/37/18775/65653/603463b2E1f70791f/05d8ad979c24be2f.jpg!q70.jpg");
            List<String> titles = new ArrayList<>();
            titles.add("1");
            titles.add("2");
            titles.add("3");
            bannerImg.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            bannerImg.setImageLoader(new MyLoader());
            bannerImg.setImages(img_url);
            bannerImg.setBannerTitles(titles);
            bannerImg.setBannerAnimation(Transformer.Default);
            bannerImg.setDelayTime(3000);
            bannerImg.isAutoPlay(true);
            bannerImg.setIndicatorGravity(BannerConfig.CENTER)
                    //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                    .setOnBannerListener(listener)
                    //必须最后调用的方法，启动轮播图。
                    .start();
        }

        private void initView(View view) {
            bannerImg = (Banner) view.findViewById(R.id.banner_img);
            tvTitle4 = (TextView) view.findViewById(R.id.tv_title4);
            tvTitleChild4 = (TextView) view.findViewById(R.id.tv_title_child4);
            ivLeft4 = (ImageView) view.findViewById(R.id.iv_left4);
            ivRight4 = (ImageView) view.findViewById(R.id.iv_right4);
            tvTitle1 = (TextView) view.findViewById(R.id.tv_title1);
            tvTitleChild1 = (TextView) view.findViewById(R.id.tv_title_child1);
            ivLeft1 = (ImageView) view.findViewById(R.id.iv_left1);
            ivRight1 = (ImageView) view.findViewById(R.id.iv_right1);
            tvTitle2 = (TextView) view.findViewById(R.id.tv_title2);
            tvTitleChild2 = (TextView) view.findViewById(R.id.tv_title_child2);
            ivLeft2 = (ImageView) view.findViewById(R.id.iv_left2);
            ivRight2 = (ImageView) view.findViewById(R.id.iv_right2);
            tvTitle3 = (TextView) view.findViewById(R.id.tv_title3);
            tvTitleChild3 = (TextView) view.findViewById(R.id.tv_title_child3);
            ivLeft3 = (ImageView) view.findViewById(R.id.iv_left3);
            ivRight3 = (ImageView) view.findViewById(R.id.iv_right3);
        }

        /**
         * 轮播监听
         */
        private OnBannerListener listener = new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, "您点击了" + img_title.get(position), Toast.LENGTH_LONG).show();
            }
        };

        /**
         * 图片加载器
         */
        private class MyLoader extends ImageLoader {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String) path).into(imageView);
            }
        }
    }

    /**
     * 秒杀专区内部类
     */
    private class SpikeViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private RecyclerView rvSpike;

        public SpikeViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            rvSpike = itemView.findViewById(R.id.rv_spike);
        }

        public void setData() {

            String[] imgs = {"https://gd4.alicdn.com/imgextra/i1/4040701594/O1CN01x14cHT1Ne5zYofdRm_!!4040701594.jpg_400x400.jpg",
                    "http://img12.360buyimg.com/n1/s450x450_jfs/t1/174949/34/1132/126466/6062f299E45fd6334/941d7b48aca9f901.jpg",
                    "http://img13.360buyimg.com/n1/jfs/t1/132093/20/13354/34370/5f924b27Edadd4b1f/f1ce078447575167.jpg",
                    "http://img10.360buyimg.com/n1/jfs/t1/107928/25/3043/196739/5e0d5a5cEaf1dbc6e/9557e2b8129addc4.jpg",
                    "http://img13.360buyimg.com/n1/jfs/t1/104027/27/18094/95944/5e901ad7Ed2ce7f82/8ed74eaf42e5b7ff.jpg"};
            String[] newPrices = {"1378", "1999", "699", "2599", "558"};

            List<Map<String, String>> mapList = new ArrayList<>();
            Map<String, String> map;
            for (int i = 0; i < imgs.length; i++) {
                map = new HashMap<>();
                map.put("imgs", imgs[i]);
                map.put("newPrices", newPrices[i]);
                mapList.add(map);
            }

            rvSpike.setAdapter(new ChildSpikeRecyclerAdapter(mContext, mapList));
            rvSpike.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));


        }
    }

    /**
     * 福利专区内部类
     */
    private class WelfareViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private RecyclerView welfareRecyclerView;

        public WelfareViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.mContext = context;
            welfareRecyclerView = itemView.findViewById(R.id.rv_welfare_recyclerView);
        }

        public void setData() {
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
            String[] newPrices = {"￥4599.00", "￥4699.00", "￥28.90", "￥319.00", "￥65.90"};
            String[] oldPrices = {"￥4599.00", "￥4699.00", "￥59.80", "￥399.00", "￥89.90"};

            List<Map<String, String>> mapList = new ArrayList<>();
            Map<String, String> map;
            for (int i = 0; i < imgs.length; i++) {
                map = new HashMap<>();
                map.put("imgs", imgs[i]);
                map.put("descriptions", descriptions[i]);
                map.put("newPrices", newPrices[i]);
                map.put("oldPrices", oldPrices[i]);
                mapList.add(map);
            }

            welfareRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            welfareRecyclerView.setAdapter(new ChildWelfareRecyclerAdapter(mContext, mapList));
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
                Toast.makeText(mContext, "您点击了" + img_title.get(position), Toast.LENGTH_LONG).show();
            }
        };

    }

}
