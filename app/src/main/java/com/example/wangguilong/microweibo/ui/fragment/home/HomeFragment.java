package com.example.wangguilong.microweibo.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.BaseAdapter;
import com.example.wangguilong.microweibo.adapter.HomeAdapter;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.ui.activity.weibo.WeiboDetailActivity;
import com.example.wangguilong.microweibo.ui.fragment.base.BaseFragment;
import com.example.wangguilong.microweibo.util.Util;
import com.example.wangguilong.microweibo.view.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView, BaseAdapter.OnItemClickListener {

	private ShimmerRecyclerView shimmerRecyclerView;
	private HomePresenter homePresenter;
//	private List<HomeBean> list = new ArrayList<>();
	private TestBean testBean;
	private HomeAdapter adapter;
	private List<TestBean> list =new ArrayList<>();
//	private CardAdapter cardAdapter;

	@Override
	protected int setLayout() {
		return R.layout.fragment_home;
	}

	@Override
	protected void initView() {
		//初始化P层
		homePresenter = new HomePresenter(this);
		homePresenter.getData();
		//设置适配器
		setAdapter();
		//设置监听
		setListener();
	}

	private void setListener() {
		adapter.setOnItemClickListener(this);
	}

	private void setAdapter() {
//		cardAdapter = new CardAdapter(getcontext(),list);
		list.add(testBean);
		adapter  = new HomeAdapter(getcontext(),shimmerRecyclerView,list,R.layout.layout_card_item);
		shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(getcontext()));

		shimmerRecyclerView.setAdapter(adapter);
		shimmerRecyclerView.showShimmerAdapter();
		shimmerRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
				shimmerRecyclerView.hideShimmerAdapter();
			}
		}, 2000);
	}

	@Override
	protected void initData() {
		findView();

	}
	private void findView() {
		shimmerRecyclerView = findViewById(R.id.shimmer_recycler_view);
	}

	@Override
	public Context getcontext() {
		return getMContext();
	}

	@Override
	public void showMsg(String msg) {
		Util.t(getcontext(),msg);
	}

	@Override
	public void getDataSuccess(TestBean bean) {
		testBean = bean;
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(View view, int position) {
		switch (view.getId()) {
			case R.id.profile_image:
				Util.t(getcontext(),"点击了头像");
				break;
			case R.id.sss:
				startActivity(new Intent(getcontext(), WeiboDetailActivity.class));
				break;
			case R.id.repost_image:
				Util.t(getcontext(),"点击了转发");
				break;
		}
	}

//	@Override
//	public void onItemClick(View v) {
//		switch (v.getId()) {
//			case R.id.profile_image:
//				Util.t(getcontext(),"点击了头像");
//				break;
//			case R.id.sss:
//				startActivity(new Intent(getcontext(), WeiboDetailActivity.class));
//				break;
//			case R.id.repost_image:
//				Util.t(getcontext(),"点击了转发");
//				break;
//		}
//
//	}
}
