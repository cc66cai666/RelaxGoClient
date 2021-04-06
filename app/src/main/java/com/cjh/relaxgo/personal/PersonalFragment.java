package com.cjh.relaxgo.personal;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;
import com.cjh.relaxgo.login.activity.LoginActivity;
import com.cjh.relaxgo.personal.adapter.MyGridViewAdapter;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private Button btnToLogin;
    private ImageView ivHeadPortrait;
    private CircleImageView civHeadPortrait;
    private FrameLayout frameLayout;
    private GridView gridView;
    private RelativeLayout relativeLayout;

    private int[] icons = {R.mipmap.personal_icon_payment,R.mipmap.personal_icon_receipt,
                            R.mipmap.personal_icon_evaluate, R.mipmap.personal_icon_collect,
                            R.mipmap.personal_icon_shop, R.mipmap.personal_icon_discount_card,R.mipmap.personal_icon_record};
    private int[] titles = {R.string.text_payment,R.string.text_receipt,R.string.text_evaluate,
                            R.string.text_collect,R.string.text_shop,
                            R.string.text_discount_card,R.string.text_record};

    @Override
    protected int initView() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initFindViewById(View view) {
//        view.setFitsSystemWindows(true);
        frameLayout = view.findViewById(R.id.fl_personal);
//        frameLayout.setFitsSystemWindows(true);
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

        gridView = view.findViewById(R.id.personal_gridView);
        gridView.setAdapter(new MyGridViewAdapter(getContext(),icons,titles));

        relativeLayout = view.findViewById(R.id.rl_personal_my_order_form);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_toLogin:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }
}
