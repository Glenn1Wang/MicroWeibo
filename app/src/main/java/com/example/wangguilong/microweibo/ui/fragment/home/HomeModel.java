package com.example.wangguilong.microweibo.ui.fragment.home;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.HomeBean;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;
import com.example.wangguilong.microweibo.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class HomeModel implements HomeContract.IHomeModel {
//	@Override
//	public void getData(Context context,final OnHttpCallBack<List<HomeBean>> callBack) {
//		List<HomeBean> list = new ArrayList<>();
//		OkGo.<String>get("https://api.weibo.com/2/statuses/home_timeline.json")
//				.params("access_token",Util.getAccessToken(context))
////				.params("count",1)
////				.params("feature",2)
//				.execute(new StringCallback() {
//					@Override
//					public void onSuccess(Response<String> response) {
////						praseJson(response.body());
////						Log.e("qwer", "onSuccess111: "+response.body());
////						LogUtil.e("qwer","onSuccess222: "+response.body());
////						com.example.wangguilong.microweibo.util.LogUtil.e("qwer","onSuccess3333: "+response.body());
//						callBack.onSuccess(praseJson(response.body()));
//					}
//				});
//						//发起网络请求

						//解析json

						//传入回调接口中
//		OkGo.<String>get("https://api.weibo.com/2/statuses/show.json")
//				.params("access_token",Util.getAccessToken(context))
//				.params("id",4218522164876826L)
//				.execute(new StringCallback() {
//					@Override
//					public void onSuccess(Response<String> response) {
////						praseJson(response.body());
//						Log.e("qwer", "onSuccess: "+response.body());
//					}
//				});
//	}

//	private HomeBean praseJson(String json) {
//
//
//
//
//
////		List<HomeBean> list = new ArrayList<>();
////		try {
////
////			org.json.JSONObject jsonObject = new org.json.JSONObject(json);
////
////			JSONArray jsonArray = jsonObject.getJSONArray("statuses");
////			for (int i=0;i<jsonArray.length();i++) {
////				HomeBean bean = new HomeBean();
////				String name = jsonArray.getJSONObject(i).getJSONObject("user").optString("name");
////				String text = jsonArray.getJSONObject(i).optString("text");
////				String id = jsonArray.getJSONObject(i).optString("id");
////				int reposts_count = jsonArray.getJSONObject(i).optInt("reposts_count");
////				int comments_count = jsonArray.getJSONObject(i).optInt("comments_count");
////				int attitudes_count = jsonArray.getJSONObject(i).optInt("attitudes_count");
////				bean.setText(text);
////				bean.setUserName(name);
////				bean.setRepostsCount(reposts_count);
////				bean.setCommentsCount(comments_count);
////				bean.setAttitudesCount(attitudes_count);
////				list.add(bean);
//////				Log.e("qwer", "praseJson: "+id+"..."+name+":"+text);
////			}
////		} catch (JSONException e) {
////			e.printStackTrace();
////		}
////		return list;
//	}

	@Override
	public void getData(Context context, final OnHttpCallBack<TestBean> callBack) {
		OkGo.<String>get("https://api.weibo.com/2/statuses/home_timeline.json")
				.params("access_token",Util.getAccessToken(context))
//				.params("count",1)
//				.params("feature",2)
				.execute(new StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Gson gson = new Gson();
						TestBean testBean = gson.fromJson(response.body(), TestBean.class);
						callBack.onSuccess(testBean);
					}
				});
	}
}
