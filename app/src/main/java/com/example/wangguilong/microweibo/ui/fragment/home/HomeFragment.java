package com.example.wangguilong.microweibo.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView, BaseAdapter.OnItemClickListener, BaseAdapter.OnLoadMoreListener {

	private ShimmerRecyclerView shimmerRecyclerView;
	private HomePresenter homePresenter;
	private HomeAdapter adapter;
	private List<TestBean.StatusesBean> beans = new ArrayList<>();
	private int page = 1;

	@Override
	protected int setLayout() {
		return R.layout.fragment_home;
	}

	@Override
	protected void initView() {
		//初始化P层
		homePresenter = new HomePresenter(this);
		homePresenter.getData(page);
		//设置适配器
		setAdapter();
		//设置监听
		setListener();
	}

	private void setListener() {
		adapter.setOnItemClickListener(this);
		adapter.setOnLoadMoreClickListener(this);
//		shimmerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//			@Override
//			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//				super.onScrolled(recyclerView, dx, dy);
//				LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
//				//可见item
//				int visibleItemCount = manager.getChildCount();
//				//全部item
//				int totalItemCount = manager.getItemCount();
//				if (!recyclerView.canScrollVertically(1)) {
//					//如果全部item大于可见item
//					if (totalItemCount > visibleItemCount) {
//						adapter.setLoading(true);
//						page++;
//						homePresenter.getData(page);
//						adapter.notifyDataSetChanged();
//					}
//				}
//			}
//		});
	}

	private void setAdapter() {
//		cardAdapter = new CardAdapter(getcontext(),list);


		adapter  = new HomeAdapter(getcontext(),shimmerRecyclerView, beans,R.layout.layout_card_item);
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
	public void getDataSuccess(List<TestBean.StatusesBean> list) {
		Log.e("qwer", "getDataSuccess: 当前page为"+page);
		if (list != null&& list.size()>0) {
			if (page == 1) {
				beans.clear();
			}
			beans.addAll(list);
		}  else {
			if (page == 1 && list.size() == 0) {
				Util.t(getcontext(),"无数据");
			}
		}
//		if (this.beans.size() == 0) {
//			this.beans.addAll(list);
//		} else {
//			adapter.addAll(list);
//		}

//		testBean = bean;
//		list.add(bean);
//		if (list.size()!=0) {
//			for (TestBean testBean:list) {
//				beans.addAll(testBean.getStatuses());
//			}
//		}
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

	@Override
	public void onLoadMore() {
		page++;
		Log.e("qwer", "onLoadMore: 当前page为"+page);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					homePresenter.getData(page);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();


//		adapter.setLoading(false);
//		adapter.notifyDataSetChanged();
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
