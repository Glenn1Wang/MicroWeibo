package com.example.wangguilong.microweibo.ui.activity.weibo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.WeiboDetailAdapter;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.util.LogUtil;
import com.example.wangguilong.microweibo.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class WeiboDetailActivity extends BaseActivity {

	private RecyclerView recyclerView;
	private TestBean.StatusesBean statusesBean;
	private WeiboDetailAdapter adapter;

	@Override
	protected int setLayout() {
		return R.layout.activity_weibo_detail;
	}

	@Override
	protected void initData() {
		super.initData();
		Intent intent = getIntent();
		if (intent != null) {
			statusesBean = (TestBean.StatusesBean) intent.getSerializableExtra("beans");
		}
//		getSupportFragmentManager().beginTransaction().replace(R.id.weibo_detail_container,new WeiboDetailFragment()).commit();
		findView();
		setAdapter();

		OkGo.<String>get("https://api.weibo.com/2/statuses/show.json")
				.params("access_token", Util.getAccessToken(this))
				.params("id",statusesBean.getId())
				.execute(new StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Gson gson = new Gson();
						LogUtil.ee("detail" +response.body());
					}

					@Override
					public void onError(Response<String> response) {
						super.onError(response);
					}
				});


		OkGo.<String>get("https://api.weibo.com/2/comments/show.json")
				.params("access_token", Util.getAccessToken(this))
				.params("id",statusesBean.getId())
				.execute(new StringCallback() {
					@Override
					public void onSuccess(Response<String> response) {
						Gson gson = new Gson();
						LogUtil.ee("comments" +response.body());
					}

					@Override
					public void onError(Response<String> response) {
						super.onError(response);
					}
				});
	}

	private void setAdapter() {
//		List<>
//	 adapter = new WeiboDetailAdapter(this,recyclerView,);
	}


	private void findView() {
		recyclerView = findViewById(R.id.detail_recyclerview);
	}
}
