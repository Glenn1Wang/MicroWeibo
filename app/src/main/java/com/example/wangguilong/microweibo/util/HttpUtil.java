package com.example.wangguilong.microweibo.util;

import android.text.TextUtils;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by WangGuiLong on 2018/3/16.
 */

public class HttpUtil {
	private static final String URL = "https://api.weibo.com/2/";
//	public void getHomeWeibo(Context context, HashMap<String,Object> params,final OnHttpCallBack<List<HomeBean>> callBack){
//		List<HomeBean> list = new ArrayList<>();
//		OkGo.<String>get(URL+"statuses/home_timeline.json")
//				.params("access_token",(String)params.get("access_token"))
//				.params("page",(int)params.get("page"))
//				.execute(new StringCallback() {
//					@Override
//					public void onSuccess(Response<String> response) {
//						callBack.onSuccess(praseJson(response.body()));
//					}
//				});
//	}
//	private List<HomeBean> praseJson(String json) {
//		List<HomeBean> list = new ArrayList<>();
//		try {
//			org.json.JSONObject jsonObject = new org.json.JSONObject(json);
//
//			JSONArray jsonArray = jsonObject.getJSONArray("statuses");
//			for (int i=0;i<jsonArray.length();i++) {
//				HomeBean bean = new HomeBean();
//				String name = jsonArray.getJSONObject(i).getJSONObject("user").optString("name");
//				String text = jsonArray.getJSONObject(i).optString("text");
//				bean.setText(text);
//				bean.setUserName(name);
//				list.add(bean);
////				Log.e("qwer", "praseJson: "+name+":"+text);
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	public static String toURLEncoded(String originUrl) {
		if (TextUtils.isEmpty(originUrl)) {
			return "";
		}
		try {
			java.net.URL url = new URL(originUrl);
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			return uri.toURL().toString();
		} catch (MalformedURLException e) {
		} catch (URISyntaxException e) {
		}
		return originUrl;
	}
}
