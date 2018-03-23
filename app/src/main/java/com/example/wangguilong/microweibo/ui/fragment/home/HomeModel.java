package com.example.wangguilong.microweibo.ui.fragment.home;

import android.content.Context;
import android.util.Log;

import com.example.wangguilong.microweibo.bean.ErrorBean;
import com.example.wangguilong.microweibo.bean.HomeBean;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;
import com.example.wangguilong.microweibo.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class HomeModel implements HomeContract.IHomeModel {
	@Override
	public void getData(Context context,int page, final OnHttpCallBack<List<TestBean.StatusesBean>> callBack) {
		OkGo.<String>get("https://api.weibo.com/2/statuses/home_timeline.json")
				.params("access_token",Util.getAccessToken(context))
//				.params("count",1)
				.params("page",page)
//				.params("feature",2)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Gson errGson = new Gson();
						ErrorBean errorBean = errGson.fromJson(response.body(), ErrorBean.class);
						if (errGson ==null) {
							Log.e("qwer", "onSuccess: "+response.body());
							Gson gson = new Gson();
							TestBean testBean = gson.fromJson(response.body(), TestBean.class);
							callBack.onSuccess(testBean.getStatuses());
						} else {
							callBack.onFail(errorBean.getError()+":"+errorBean.getError_code());
						}


					}

				});
	}
}
