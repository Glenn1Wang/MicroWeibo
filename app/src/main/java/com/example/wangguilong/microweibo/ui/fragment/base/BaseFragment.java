package com.example.wangguilong.microweibo.ui.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public abstract class BaseFragment extends Fragment {

	private View view;
	private Context context;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view = inflater.inflate(setLayout(),container,false);
		context = getContext();
		init();
		initData();
		initView();
		return view;
	}

	/**
	 * 设置布局id
	 * @return
	 */
	protected abstract int setLayout();

	/**
	 * 初始化成员变量 获取Intent传递来的数据
	 */
	protected void init() {

	}

	/**
	 * 初始化视图
	 */
	protected void initView() {

	}

	/**
	 * 初始化数据
	 */
	protected void initData() {

	}

	public Context getMContext(){
		return context;
	}

	public View getContentView(){
		return view;
	}

	/**
	 * 找id
	 * @param id
	 * @param <T>
	 * @return
	 */
	protected <T extends View> T findViewById(@IdRes int id) {
		return (T) view.findViewById(id);
	}
}
