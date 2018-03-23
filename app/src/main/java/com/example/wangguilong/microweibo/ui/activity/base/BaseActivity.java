package com.example.wangguilong.microweibo.ui.activity.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.lzy.okgo.OkGo;


/**
 * Created by WangGuiLong on 2018/3/10.
 */

public abstract class BaseActivity extends AppCompatActivity {

	private ImmersionBar mImmersionBar;
	private InputMethodManager imm;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(setLayout());
		//透明状态栏
		mImmersionBar = ImmersionBar.with(this)
				.statusBarDarkFont(true)
				.navigationBarEnable(false);
		mImmersionBar.init();

		OkGo.getInstance().init(getApplication());
		//初始化数据
		initData();
		//view与数据绑定
		initView();
		//设置监听
		setListener();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.imm = null;
		if (mImmersionBar != null)
			mImmersionBar.destroy();
	}

	@Override
	public void finish() {
		super.finish();
		hideSoftKeyBoard();
	}

	//隐藏软键盘
	public void hideSoftKeyBoard() {
		View localView = getCurrentFocus();
		if (this.imm == null) {
			this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
		}
		if ((localView != null) && (this.imm != null)) {
			this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
		}
	}

	protected void setListener() {
	}

	protected void initView() {
	}

	protected void initData() {
	}

	/**
	 * @return 布局文件id
	 */
	protected abstract int setLayout();


}
