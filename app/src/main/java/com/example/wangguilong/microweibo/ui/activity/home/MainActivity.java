package com.example.wangguilong.microweibo.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.ViewPagerAdapter;
import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.ui.activity.ff.friend.FriendsActivity;
import com.example.wangguilong.microweibo.ui.activity.message.MessageActivity;
import com.example.wangguilong.microweibo.ui.activity.profile.ProfileActivity;
import com.example.wangguilong.microweibo.ui.activity.send.SendActivity;
import com.example.wangguilong.microweibo.ui.activity.setting.SettingActivity;
import com.example.wangguilong.microweibo.ui.fragment.home.HomeFragment;
import com.example.wangguilong.microweibo.ui.fragment.message.MessageFragment;
import com.example.wangguilong.microweibo.util.Util;
import com.example.wangguilong.microweibo.view.circularimage.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.IMainView, View.OnClickListener {

    private FloatingActionButton fab;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter pagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private CircularImageView headImage;
    private TextView nameText, followsText, friendsText, descriptionText;
    private ProfileBean profileBean = null;
    private MainPresenter mainPresenter;

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findView();
        mainPresenter = new MainPresenter(this);
        long uid = Util.getUID(this);
        if (uid != 0) {
            mainPresenter.getProfileData(uid);
        }

    }


    @Override
    protected void initView() {
        super.initView();
        //个人信息显示
        //viewpager相关
        setPagerAdapter();

//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//
//        //如果未登录 显示提醒授权的fragment
//
//        //如果已登录
//
//            //如果没有网络 提醒打开网络 重试
//
//            //如果无数据  提示 无数据
//            transaction.replace(R.id.content_framelayout,new HomeFragment());
//            transaction.commit();

    }

    /**
     * 个人信息
     */
    private void setProfile() {
//        if ()
        //https://api.weibo.com/2/users/show.json
        if (profileBean != null) {
            Glide.with(MainActivity.this)
                    .load(profileBean.getAvatar_large())
                    .into(headImage);
            nameText.setText(profileBean.getName());
            followsText.setText(profileBean.getFollowers_count() + "");
            friendsText.setText(profileBean.getFriends_count() + "");
            descriptionText.setText(profileBean.getDescription());
        }

    }

    private void setPagerAdapter() {
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MessageFragment());
        titleList.add("微博");
        titleList.add("消息");
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setListener() {
        super.setListener();
        //设置fab点击事件
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                //打开编辑微博页面
                startActivity(new Intent(MainActivity.this, SendActivity.class));
            }
        });

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        headImage.setOnClickListener(this);
    }

    private void findView() {
        viewPager = findViewById(R.id.viewpager);
        fab = findViewById(R.id.fab);
        drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        headImage = view.findViewById(R.id.profile_head_image);
        nameText = view.findViewById(R.id.profile_name_text);
        followsText = view.findViewById(R.id.profile_followers_count_text);
        friendsText = view.findViewById(R.id.profile_friends_count_text);
        descriptionText = view.findViewById(R.id.profile_description_text);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("我的微博"));
        tabLayout.addTab(tabLayout.newTab().setText("消息"));
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        item.setCheckable(false);
        int id = item.getItemId();
        if (id == R.id.nav_comments) {
            // Handle the camera action
            startActivity(new Intent(this, MessageActivity.class));
            item.setChecked(false);
        } else if (id == R.id.nav_friends) {
            startActivity(new Intent(this, FriendsActivity.class));

        } else if (id == R.id.nav_drafts) {

        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(this, SettingActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public Context getcontext() {
        return this;
    }

    @Override
    public void showMsg(String msg) {
        Util.t(this, msg);
    }

    @Override
    public void getProfileDataSuccess(ProfileBean bean) {
        profileBean = bean;
//        if (bean != null) {
//            Glide.with(MainActivity.this)
//                    .load(bean.getProfile_image_url())
//                    .into(headImage);
//            nameText.setText(bean.getName());
//        }
        //头像
        setProfile();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_head_image:
                Intent intent = new Intent();
                Log.e("main", "main screen_name: "+profileBean.getScreen_name());
                if (profileBean.getScreen_name() != null) {
                    intent.putExtra("screen_name", profileBean.getScreen_name());
                    intent.setClass(MainActivity.this, ProfileActivity.class);
                    startActivity(intent);

                }
                break;
        }
    }
}
