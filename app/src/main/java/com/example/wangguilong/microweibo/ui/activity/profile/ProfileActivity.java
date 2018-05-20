package com.example.wangguilong.microweibo.ui.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.ViewPagerAdapter;
import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.ui.activity.ff.follow.FollowsActivity;
import com.example.wangguilong.microweibo.ui.activity.ff.friend.FriendsActivity;
import com.example.wangguilong.microweibo.ui.fragment.profile.about.ProfileAboutFragment;
import com.example.wangguilong.microweibo.ui.fragment.profile.weibo.ProfileWeiboFragment;
import com.example.wangguilong.microweibo.util.Util;
import com.example.wangguilong.microweibo.view.circularimage.CircularImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends BaseActivity implements ProfileContract.IProfileView, AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private Intent intent;
    private long uid;
    private String screenName;
    private ProfilePresenter profilePresenter;
    private CircularImageView headImage;
    private TextView nameText,friends,follows,title;
    private ImageView backgroundImage,backArrow;
    private CollapsingToolbarLayout toolbarLayout;
    private AppBarLayout appBarLayout;
    private TabLayout profileTabLayout;
    private ViewPager profileViewpager;
    private ViewPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private ProfileAboutFragment profileAboutFragment;
    private ProfileBean profileBean = null;
    private ProfileWeiboFragment profileWeiboFragment;
    private int flag;

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
//        setContentView();
//        initData();
//        initView();
//        setListener();
//    }

    @Override
    protected int setLayout() {
        return R.layout.activity_profile;
    }

    @Override
    protected void initData() {
        intent = getIntent();
        if (intent !=null) {
            screenName = intent.getStringExtra("screen_name");
            flag = intent.getIntExtra("flag",0);
//            Log.e("qwer", "initData: "+screenName);
        }
    }


    @Override
    protected void initView() {
        profilePresenter = new ProfilePresenter(this);
        profilePresenter.getProfileData(screenName);

        findView();

        setAdapter();
    }

    private void setAdapter() {
        fragmentList = new ArrayList<>();
        profileAboutFragment = new ProfileAboutFragment();

        profileWeiboFragment = new ProfileWeiboFragment();
        fragmentList.add(profileAboutFragment);
        fragmentList.add(profileWeiboFragment);

        titleList = new ArrayList<>();
        titleList.add("关于");
        titleList.add("微博");
        adapter = new ViewPagerAdapter(getSupportFragmentManager(),fragmentList,titleList);
        profileViewpager.setAdapter(adapter);
        profileTabLayout.setupWithViewPager(profileViewpager);
    }

    private void findView() {
        headImage = findViewById(R.id.profile_head_image);
        nameText = findViewById(R.id.profile_name_text);
        friends = findViewById(R.id.profile_friends_count_text);
        follows = findViewById(R.id.profile_followers_count_text);
        backgroundImage = findViewById(R.id.profile_background_image);
        toolbarLayout = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.appbar_layout);
        title = findViewById(R.id.title_text);
        backArrow = findViewById(R.id.back_image);
        profileTabLayout = findViewById(R.id.profile_tablayout);
        profileViewpager = findViewById(R.id.profile_viewpager);
    }

    @Override
    protected void setListener() {
        appBarLayout.addOnOffsetChangedListener(this);
        backArrow.setOnClickListener(this);
        friends.setOnClickListener(this);
        follows.setOnClickListener(this);
    }

    @Override
    public Context getcontext() {
        return this;
    }

    @Override
    public void showMsg(String msg) {
        Util.t(this,msg);
    }

    @Override
    public void getProfileDataSuccess(ProfileBean bean) {
        EventBus.getDefault().post(bean);

        this.profileBean = bean;
        Glide.with(this).load(bean.getAvatar_large()).into(headImage);
        nameText.setText(bean.getName());
        friends.setText(bean.getFriends_count()+"");
        follows.setText(bean.getFollowers_count()+"");
        Glide.with(this).load(bean.getCover_image_phone()).into(backgroundImage);
        title.setText(bean.getName());
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        Log.e("onOffsetChanged", "verticalOffset: "+Math.abs(verticalOffset) );
//        Log.e("onOffsetChanged", "appBarLayout.getTotalScrollRange(): "+appBarLayout.getTotalScrollRange());
//
        //折叠状态
//        if (getSupportActionBar().getHeight()-appBarLayout.getHeight()==verticalOffset) {
//            title.setVisibility(View.VISIBLE);
//        }

        //完全展开状态
        if (verticalOffset == 0) {
            title.setVisibility(View.GONE);
        } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
            //折叠状态
            title.setVisibility(View.VISIBLE);
        } else {
            //中间状态
            title.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.profile_friends_count_text:
                Intent intent = new Intent();
                intent.putExtra("screen_name",screenName);
//                intent.putExtra("friends",true);
                intent.setClass(this, FriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_followers_count_text:
                Intent intent1 = new Intent();
                intent1.putExtra("screen_name",screenName);
//                intent1.putExtra("friends",false);
                intent1.setClass(this, FollowsActivity.class);
                startActivity(intent1);
                break;
        }
    }

}
