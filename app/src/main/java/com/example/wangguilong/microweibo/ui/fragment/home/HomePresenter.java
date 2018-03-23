package com.example.wangguilong.microweibo.ui.fragment.home;

import android.widget.ImageView;

import com.example.wangguilong.microweibo.bean.HomeBean;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class HomePresenter implements HomeContract.IHomePresenter {
	private HomeContract.IHomeView iHomeView;
	private HomeContract.IHomeModel iHomeModel;

	public HomePresenter(HomeContract.IHomeView iHomeView) {
		this.iHomeView = iHomeView;
		iHomeModel = new HomeModel();
	}

	@Override
	public void getData(int page) {
		iHomeModel.getData(iHomeView.getcontext(),page, new OnHttpCallBack<List<TestBean.StatusesBean>>() {
			@Override
			public void onSuccess(List<TestBean.StatusesBean> statusesBeans) {
				iHomeView.getDataSuccess(statusesBeans);
			}

			@Override
			public void onFail(String msg) {
				iHomeView.showMsg(msg);
			}
		});
	}
}
