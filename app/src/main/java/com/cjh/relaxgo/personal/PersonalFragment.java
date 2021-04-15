package com.cjh.relaxgo.personal;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;
import com.cjh.relaxgo.login.activity.LoginActivity;
import com.cjh.relaxgo.personal.activity.SetActivity;
import com.cjh.relaxgo.personal.adapter.MyGridViewAdapter;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private Button btnToLogin;
    private ImageView ivHeadPortrait;
    private CircleImageView civHeadPortrait;
    private RelativeLayout rl_collect_commodity,rl_collect_shop,rl_discount_card,rl_record;
    private LinearLayout ll_payment,ll_deliver_goods,ll_receipt,ll_evaluate,ll_after_sale;
    private ImageView iv_personal_set;


    @Override
    protected int initView() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initFindViewById(View view) {
        btnToLogin = view.findViewById(R.id.btn_toLogin);
        btnToLogin.setOnClickListener(this);
        ivHeadPortrait = (ImageView) view.findViewById(R.id.iv_head_portrait_bg);
        ivHeadPortrait.setScaleType(ImageView.ScaleType.FIT_XY);
        //背景
        Glide.with(getContext())
                .load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1950846641,3729028697&fm=26&gp=0.jpg")
                .bitmapTransform(new BlurTransformation(getContext(),20,2))
                .into(ivHeadPortrait);

        civHeadPortrait = (CircleImageView) view.findViewById(R.id.civ_head_portrait);
        //头像
        Glide.with(getContext())
                .load("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1950846641,3729028697&fm=26&gp=0.jpg")
                .into(civHeadPortrait);

        ll_payment = view.findViewById(R.id.ll_payment);
        ll_payment.setOnClickListener(this);
        ll_deliver_goods = view.findViewById(R.id.ll_deliver_goods);
        ll_deliver_goods.setOnClickListener(this);
        ll_receipt = view.findViewById(R.id.ll_receipt);
        ll_receipt.setOnClickListener(this);
        ll_evaluate = view.findViewById(R.id.ll_evaluate);
        ll_evaluate.setOnClickListener(this);
        ll_after_sale = view.findViewById(R.id.ll_after_sale);
        ll_after_sale.setOnClickListener(this);

        rl_collect_commodity = view.findViewById(R.id.rl_collect_commodity);
        rl_collect_commodity.setOnClickListener(this);
        rl_collect_shop = view.findViewById(R.id.rl_collect_shop);
        rl_collect_shop.setOnClickListener(this);
        rl_discount_card = view.findViewById(R.id.rl_discount_card);
        rl_discount_card.setOnClickListener(this);
        rl_record = view.findViewById(R.id.rl_record);
        rl_record.setOnClickListener(this);

        iv_personal_set = view.findViewById(R.id.iv_personal_set);
        iv_personal_set.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.iv_personal_set:
                startActivity(new Intent(getActivity(), SetActivity.class));
                break;

                //待付款
            case R.id.ll_payment:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;

            //待发货
            case R.id.ll_deliver_goods:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;

            //待收货
            case R.id.ll_receipt:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;

            //评价
            case R.id.ll_evaluate:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;

            //售后
            case R.id.ll_after_sale:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_toLogin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;

                //商品收藏
            case R.id.rl_collect_commodity:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;


            //店铺收藏
            case R.id.rl_collect_shop:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;

            //我的优惠卷
            case R.id.rl_discount_card:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;

            //浏览痕迹
            case R.id.rl_record:
                Toast.makeText(getContext(),"点击了",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
