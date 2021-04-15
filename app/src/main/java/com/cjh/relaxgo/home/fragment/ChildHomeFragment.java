package com.cjh.relaxgo.home.fragment;


import android.view.View;
import android.widget.ImageView;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cjh.relaxgo.R;
import com.cjh.relaxgo.base.BaseFragment;
import com.cjh.relaxgo.home.adapter.ChildRecyclerViewAdapter;


import java.util.ArrayList;
import java.util.List;

public class ChildHomeFragment extends BaseFragment implements View.OnClickListener {

    private List<String> img_url;
    private List<String> img_title;
    private RecyclerView recyclerView;
    private ImageView returnTop;


    @Override
    protected int initView() {
        return R.layout.fragment_home_child;
    }

    @Override
    protected void initFindViewById(View view) {

        img_url = new ArrayList<>();
        img_title = new ArrayList<>();
        img_url.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbenyouhuifile.it168.com%2Fforum%2F201211%2F25%2F095205fdcp5p01qpu5f1k9.jpg&refer=http%3A%2F%2Fbenyouhuifile.it168.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619506182&t=ed8f98c6b69919a6124e4f72231b74e9");
        img_url.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.bbra.cn%2F%28S%28k3eoabeultbgf5fqb2l0kbb5%29%29%2FUploadfiles%2Fimgs%2F2014%2F05%2F26%2Fmm4%2FXbzs_018.jpg&refer=http%3A%2F%2Fwww.bbra.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619506231&t=df09dfe1bbb691558100ef567245914a");
        img_url.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.jj20.com%2Fup%2Fallimg%2F1114%2F0H520115I6%2F200H5115I6-4-1200.jpg&refer=http%3A%2F%2Fpic.jj20.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619506275&t=ced56e57671f5eab254e3c5f497b5981");
        img_url.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fp.qpic.cn%2Fdnfbbspic%2F0%2Fdnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_201906_17_093137cdx8ylhde8u2ebfu.jpg%2F0&refer=http%3A%2F%2Fp.qpic.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619506275&t=643c667cba3d78f5c27f93766daa0bcb");
        img_url.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fst.gdefon.com%2Fwallpapers_original%2F555175_keira_knightley_model_actress_beauty_1920x1080_www.Gde-Fon.com.jpg&refer=http%3A%2F%2Fst.gdefon.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1619506275&t=4d593c92959e146b206584b2327c3cda");

        img_title.add("美女1");
        img_title.add("美女2");
        img_title.add("美女3");
        img_title.add("美女4");
        img_title.add("美女5");

        returnTop = view.findViewById(R.id.iv_return_top);
        returnTop.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new ChildRecyclerViewAdapter(getContext(),img_url,img_title));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position <= 3){
                    returnTop.setVisibility(View.GONE);//隐藏
                }else {
                    returnTop.setVisibility(View.VISIBLE);//可见
                }
                //只能返回1
                return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);




    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        if (view == returnTop){
            recyclerView.scrollToPosition(0);//回到顶部
        }
    }
}