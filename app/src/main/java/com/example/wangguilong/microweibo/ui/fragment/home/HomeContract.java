package com.example.wangguilong.microweibo.ui.fragment.home;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class HomeContract {

	interface IHomeView {
		Context getcontext();
		void showMsg(String msg);
		void getDataSuccess(List<TestBean.StatusesBean> bean);
	}

	interface IHomePresenter {
		void getData(int page);
	}

	interface IHomeModel {
		void getData(Context context, int page,OnHttpCallBack<List<TestBean.StatusesBean>> callBack);
	}
}
