package com.example.wangguilong.microweibo.ui.activity.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.ui.activity.home.MainActivity;
import com.example.wangguilong.microweibo.ui.activity.login.LoginActivity;
import com.example.wangguilong.microweibo.util.Util;

public class WelcomeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_welcome);
//		//创建数据库
//		Connector.getDatabase();

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (!TextUtils.isEmpty(Util.getAccessToken(WelcomeActivity.this))) {
					startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
				} else {
					startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
				}

				finish();
			}
		},1000);
	}
}
