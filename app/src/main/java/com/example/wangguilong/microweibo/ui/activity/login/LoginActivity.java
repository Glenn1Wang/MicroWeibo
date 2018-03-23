package com.example.wangguilong.microweibo.ui.activity.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.wangguilong.microweibo.weibo.Constants;
import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.ui.activity.home.MainActivity;
import com.example.wangguilong.microweibo.util.Util;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

	private TextView loginBtn;
	private SsoHandler ssoHandler;
	private SharedPreferences preferences;
	@Override
	protected int setLayout() {
		return R.layout.activity_login;
	}

	@Override
	protected void initData() {
		super.initData();
		findView();
		//创建微博API接口类对象
		AuthInfo mAuthInfo = new AuthInfo(this, Constants.APP_KEY,Constants.REDIRECT_URL,Constants.SCOPE);
		WbSdk.install(this,mAuthInfo);
	}

	@Override
	protected void initView() {
		super.initView();

	}

	@Override
	protected void setListener() {
		super.setListener();
		loginBtn.setOnClickListener(this);
	}

	private void findView() {
		loginBtn = findViewById(R.id.login_in);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.login_in:
				getAccess();
				break;
				default:
					break;
		}
	}

	private void getAccess() {
		ssoHandler = new SsoHandler(this);
		//authorizeClientSso 仅客户端
//		ssoHandler.authorizeClientSso(new SelfWbAuthListener());
		//authorize ALL IN ONE
		ssoHandler.authorize(new SelfWbAuthListener());
		//authorizeWeb 仅网页
//		ssoHandler.authorizeWeb(new SelfWbAuthListener());
	}

	private class SelfWbAuthListener implements WbAuthListener {

		@Override
		public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
			Util.t(LoginActivity.this,"success");
//			Log.e("qwer", "onSuccess: "+oauth2AccessToken.getToken());
			//将token存到sharedpreference中
			preferences = getSharedPreferences("user_info",MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			editor.putString("access_token",oauth2AccessToken.getToken());
			Log.e("qwer", "onSuccess: "+oauth2AccessToken.getToken());
			editor.commit();
			startActivity(new Intent(LoginActivity.this, MainActivity.class));
		}

		@Override
		public void cancel() {
			Util.t(LoginActivity.this,"cancel");
		}

		@Override
		public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
			String error = wbConnectErrorMessage.getErrorMessage();
			String code = wbConnectErrorMessage.getErrorCode();
			Util.t(LoginActivity.this,"Failure");
			Log.e("qwer", "onFailure: "+error+code);
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (ssoHandler != null) {
			ssoHandler.authorizeCallBack(requestCode, resultCode, data);
		}
	}
}
