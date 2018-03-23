package com.example.wangguilong.microweibo.util;

import android.content.Context;
import android.support.annotation.StringDef;
import android.util.Log;

import com.example.wangguilong.microweibo.bean.HomeBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/16.
 */

public class HttpUtil {
	private static final String URL = "https://api.weibo.com/2/";
	public void getHomeWeibo(Context context, HashMap<String,Object> params,final OnHttpCallBack<List<HomeBean>> callBack){
		List<HomeBean> list = new ArrayList<>();
		OkGo.<String>get(URL+"statuses/home_timeline.json")
				.params("access_token",(String)params.get("access_token"))
				.params("page",(int)params.get("page"))
				.execute(new StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						callBack.onSuccess(praseJson(response.body()));
					}
				});
	}
	private List<HomeBean> praseJson(String json) {
		List<HomeBean> list = new ArrayList<>();
		try {
			org.json.JSONObject jsonObject = new org.json.JSONObject(json);

			JSONArray jsonArray = jsonObject.getJSONArray("statuses");
			for (int i=0;i<jsonArray.length();i++) {
				HomeBean bean = new HomeBean();
				String name = jsonArray.getJSONObject(i).getJSONObject("user").optString("name");
				String text = jsonArray.getJSONObject(i).optString("text");
				bean.setText(text);
				bean.setUserName(name);
				list.add(bean);
//				Log.e("qwer", "praseJson: "+name+":"+text);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
}
