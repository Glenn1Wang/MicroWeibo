package com.example.wangguilong.microweibo.ui.activity.message;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.ViewPagerAdapter;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.ui.fragment.message.atcomment.AtCommentFragment;
import com.example.wangguilong.microweibo.ui.fragment.message.atweibo.AtWeiboFragment;
import com.example.wangguilong.microweibo.ui.fragment.message.bmcomment.BmCommentFragment;
import com.example.wangguilong.microweibo.ui.fragment.message.recomment.ReCommentFragment;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private List<String> titleList;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private Intent intent;
    private int tabPage = 0;
    private ImageView back;

    @Override
    protected int setLayout() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {
        super.initData();
        intent = getIntent();
        if (intent != null) {
            tabPage = intent.getIntExtra("page",0);
        }
        findView();
        initTablayout();
        setAdapter();
    }

    @Override
    protected void initView() {
        super.initView();
    }

    private void setAdapter() {
        titleList = new ArrayList<>();
        titleList.add("@我的微博");
        titleList.add("@我的评论");
        titleList.add("收到的评论");
        titleList.add("发出的评论");
        fragmentList = new ArrayList<>();
        fragmentList.add(new AtWeiboFragment());
        fragmentList.add(new AtCommentFragment());
        fragmentList.add(new ReCommentFragment());
        fragmentList.add(new BmCommentFragment());
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);
        viewPager.setAdapter(adapter);
        tabLayout.getTabAt(tabPage).select();
        viewPager.setCurrentItem(tabPage);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void initTablayout() {
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
    }
    private void findView() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        back = findViewById(R.id.back_image);
    }

    @Override
    protected void setListener() {
        super.setListener();
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_image:
                finish();
                break;
        }
    }
}


