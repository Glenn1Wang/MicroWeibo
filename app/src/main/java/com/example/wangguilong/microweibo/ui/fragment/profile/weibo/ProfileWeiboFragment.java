package com.example.wangguilong.microweibo.ui.fragment.profile.weibo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.ProfileWeiboAdapter;
import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.bean.ProfileWeiboBean;
import com.example.wangguilong.microweibo.ui.fragment.base.BaseFragment;
import com.example.wangguilong.microweibo.util.Util;
import com.example.wangguilong.microweibo.view.shimmer.ShimmerRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class ProfileWeiboFragment extends BaseFragment implements ProfileWeiboContract.IProfileWeiboView{
	private ShimmerRecyclerView shimmerRecyclerView;
	private ProfileWeiboAdapter adapter;
	private List<ProfileWeiboBean.StatusesBean> beans = new ArrayList<>();
	private ProfileWeiboPresenter presenter;
	private String screenName = null;
	private int page = 1;

	@Override
	protected int setLayout() {
		return R.layout.fragment_profile_weibo;
	}

	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onProfileAboutEvent(ProfileBean bean) {
//        this.bean = bean;
		Log.e("profileWeibo", "onProfileAboutEvent: "+bean.getScreen_name());

		if (bean !=null) {
			screenName = bean.getScreen_name();
			presenter.getData(screenName,page);
			ProfileWeiboBean.StatusesBean bean1 = new ProfileWeiboBean.StatusesBean();
			bean1.setText(bean.getStatus().getText());

			ProfileWeiboBean.StatusesBean.UserBean userBean = new ProfileWeiboBean.StatusesBean.UserBean();
			userBean.setName(bean.getScreen_name());
			userBean.setProfile_image_url(bean.getProfile_image_url());
			bean1.setUser(userBean);

			bean1.setCreated_at(bean.getCreated_at());
			bean1.setComments_count(bean.getStatus().getComments_count());

			bean1.setReposts_count(bean.getStatus().getReposts_count());


			beans.add(bean1);
			adapter.notifyDataSetChanged();
		}

	}
	@Override
	protected void init() {
		EventBus.getDefault().register(this);

	}
	@Override
	protected void initView() {

		//设置适配器
		setAdapter();
		Log.e("profileWeibo", "initView: ");

	}

	@Override
	protected void initData() {
		presenter = new ProfileWeiboPresenter(this);
//		presenter.getData(screenName,page);

		findView();
	}
	/**
	 * 设置适配器
	 */
	private void setAdapter() {
		adapter = new ProfileWeiboAdapter(getMContext(),shimmerRecyclerView, beans,R.layout.layout_card_item);
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




	private void findView() {
		shimmerRecyclerView = findViewById(R.id.shimmer_recycler_view);
	}

	@Override
	public Context getcontext() {
		return getMContext();
	}

	@Override
	public void showMsg(String msg) {
		Util.t(getMContext(),msg);
	}

	@Override
	public void getDataSuccess(List<ProfileWeiboBean.StatusesBean> bean) {
		if (bean != null) {
//			bean.get(0).getText();
			Log.e("qwer", "getDataSuccess111:  != null");
			beans.addAll(bean);
			adapter.notifyDataSetChanged();
		}
		Log.e("qwer", "getDataSuccess111:  == null");

//		Log.e("qwer", "getDataSuccess111: "+bean.get(0).getText() );
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

}
