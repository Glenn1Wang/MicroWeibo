package com.example.wangguilong.microweibo.ui.activity.ff.friend;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.FriendsAdapter;
import com.example.wangguilong.microweibo.bean.FriendsBean;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.util.Util;

import java.util.ArrayList;
import java.util.List;

public class FriendsActivity extends BaseActivity implements FriendsContract.IFriendsView, View.OnClickListener {


    private String screenName = null;
    private int cursor = 0;
    private FriendsPresenter presenter;
    private RecyclerView recyclerView;
    private FriendsAdapter adapter;
    private List<FriendsBean.UsersBean> list = new ArrayList<>();
//    private boolean isFriends = true;
    private TextView title;
    private ImageView back;

    @Override
    protected int setLayout() {
        return R.layout.activity_friends;
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent != null) {
            screenName = intent.getStringExtra("screen_name");
//            isFriends = intent.getBooleanExtra("friends",true);
        }
        presenter = new FriendsPresenter(this);

        findView();
//        if (isFriends) {
//            title.setText("我的好友");
            presenter.getFriendsData(screenName,cursor);
//        } else {
//            title.setText("我的粉丝");
//            presenter.getFollowsData(screenName,cursor);
//        }

    }

    @Override
    protected void initView() {
        super.initView();

        setAdapter();
    }

    @Override
    protected void setListener() {
        super.setListener();
        back.setOnClickListener(this);
    }

    private void setAdapter() {
        adapter = new FriendsAdapter(this,recyclerView,list,R.layout.layout_friends_item);
        LinearLayoutManager manager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void findView() {
        recyclerView = findViewById(R.id.friends_recycler);
        title = findViewById(R.id.title);
        back = findViewById(R.id.back_image);
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
    public void getFriendsDataSuccess(FriendsBean bean) {
        if (bean!= null){
//            if (bean.getNext_cursor()<20)  {
            if (bean.getUsers()!= null) {

                list.addAll(bean.getUsers());
                adapter.notifyDataSetChanged();
                Log.e("qwer", "getFriendsDataSuccess: "+bean.getNext_cursor() );
//                presenter.getFriendsData(screenName,bean.getNext_cursor());
//            }
            }
        }

    }

//    @Override
//    public void getFollowsDataSuccess(FriendsBean bean) {
//        if (bean!= null){
//            if (bean.getNext_cursor()<20)  {
//                list.addAll(bean.getUsers());
//                adapter.notifyDataSetChanged();
//                Log.e("qwer", "getFriendsDataSuccess: "+bean.getNext_cursor() );
//                presenter.getFriendsData(screenName,bean.getNext_cursor());
//            }
////            friendsBean = bean;
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_image:
                finish();
                break;
        }
    }
}
