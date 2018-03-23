package com.example.wangguilong.microweibo.ui.activity.home;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.ui.fragment.discover.DiscoverFragment;
import com.example.wangguilong.microweibo.ui.fragment.home.HomeFragment;
import com.example.wangguilong.microweibo.ui.fragment.message.MessageFragment;
import com.example.wangguilong.microweibo.ui.fragment.profile.ProfileFragment;
import com.example.wangguilong.microweibo.view.dialog.CustomDialog;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements View.OnClickListener {

	private LinearLayout tabHome,tabMessage,tabDiscover,tabProfile;
	private List<Fragment> fragmentList = new ArrayList<>();
	private Fragment preFragment;
	private ImageView tabCompose;
	private HomeFragment homeFragment;
	private CustomDialog dialog;

	@Override
	protected int setLayout() {
		return R.layout.activity_main;
	}

	@Override
	protected void initData() {
		super.initData();
		findView();
		//初始化fragment
		initFragment();
	}

	/**
	 * 初始化fragment
	 */
	private void initFragment() {
		homeFragment = new HomeFragment();
		MessageFragment messageFragment = new MessageFragment();
		DiscoverFragment discoverFragment = new DiscoverFragment();
		ProfileFragment profileFragment = new ProfileFragment();
		fragmentList.add(homeFragment);
		fragmentList.add(messageFragment);
		fragmentList.add(discoverFragment);
		fragmentList.add(profileFragment);
		fragmentList.add(new Fragment());
	}

	@Override
	protected void initView() {
		super.initView();
		//设置默认点击
		defaultView();
	}

	/**
	 * 设置默认点击
	 */
	private void defaultView() {
		preFragment=fragmentList.get(4);
		addContent(fragmentList.get(0));
		preFragment=fragmentList.get(0);
		tabSelected(tabHome);
	}

	private void findView() {
		tabHome = findViewById(R.id.tab_home_linear);
		tabMessage = findViewById(R.id.tab_message_linear);
		tabDiscover = findViewById(R.id.tab_discover_linear);
		tabProfile = findViewById(R.id.tab_profile_linear);
		tabCompose = findViewById(R.id.tab_compose_image);
	}

	@Override
	protected void setListener() {
		super.setListener();
		tabHome.setOnClickListener(this);
		tabMessage.setOnClickListener(this);
		tabDiscover.setOnClickListener(this);
		tabProfile.setOnClickListener(this);
		tabCompose.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.tab_home_linear:  //微博
				addContent(fragmentList.get(0));
				preFragment = fragmentList.get(0);
				tabSelected(tabHome);
				break;
			case R.id.tab_message_linear: //消息
				addContent(fragmentList.get(1));
				preFragment = fragmentList.get(1);
				tabSelected(tabMessage);
				break;
			case R.id.tab_discover_linear: //发现
				addContent(fragmentList.get(2));
				preFragment = fragmentList.get(2);
				tabSelected(tabDiscover);
				break;
			case R.id.tab_profile_linear: //我
				addContent(fragmentList.get(3));
				preFragment = fragmentList.get(3);
				tabSelected(tabProfile);
				break;
			case R.id.tab_compose_image: //加号
				showdialog();
				break;
			case R.id.close_dialog_linear:
				if (dialog != null) {
					dialog.dismiss();
				}
				default:
					break;
		}
	}

	private void showdialog() {
		CustomDialog.Builder builder = new CustomDialog.Builder(this);
		dialog = builder.view(R.layout.layout_compose_detail)
				.style(R.style.CustomDialog)
				.heightDp(300)
				.setGravity(Gravity.BOTTOM)
				.touchOutToCancel(true)
				.addViewOnclick(R.id.close_dialog_linear,this)
				.build();
		dialog.show();
	}

	private void addContent(Fragment fragment) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		if (!fragment.isAdded()){
			ft.hide(preFragment).add(R.id.framelayout,fragment);

		}else {
			ft.hide(preFragment).show(fragment);
		}
		ft.commit();
	}
	/**
	 * 设置tab的点击
	 * @param linearLayout
	 */
	private void tabSelected(LinearLayout linearLayout) {
		tabHome.setSelected(false);
		tabMessage.setSelected(false);
		tabDiscover.setSelected(false);
		tabProfile.setSelected(false);
		linearLayout.setSelected(true);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==KeyEvent.KEYCODE_BACK){
			moveTaskToBack(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
