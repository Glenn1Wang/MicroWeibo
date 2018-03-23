package com.example.wangguilong.microweibo.ui.activity.weibo;

import android.support.v7.widget.RecyclerView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;

public class WeiboDetailActivity extends BaseActivity {

	private RecyclerView recyclerView;
	@Override
	protected int setLayout() {
		return R.layout.activity_weibo_detail;
	}

	@Override
	protected void initData() {
		super.initData();
//		getSupportFragmentManager().beginTransaction().replace(R.id.weibo_detail_container,new WeiboDetailFragment()).commit();
		findView();
	}

	private void findView() {
		recyclerView = findViewById(R.id.detail_recyclerview);
	}
}
