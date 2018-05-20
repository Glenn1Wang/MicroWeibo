package com.example.wangguilong.microweibo.ui.fragment.profile.about;

import android.util.Log;
import android.widget.TextView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.ui.fragment.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class ProfileAboutFragment extends BaseFragment {

//    private ProfileBean bean = null;
    private TextView introduct,address,sex,register;
    private String sexData = null;
    private String introductData = null;
    private String addressData = null;

    @Override
    protected int setLayout() {
        return R.layout.fragment_profile_about;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProfileAboutEvent(ProfileBean bean) {
//        this.bean = bean;
        Log.e("about11111", "onProfileAboutEvent: "+bean.getLocation()+bean.getDescription());
        if (bean !=null) {
            introductData = bean.getDescription();
            addressData = bean.getLocation();
            if (bean.getGender().equals("m")) {
                sexData = "男";
            } else if (bean.getGender().equals("f")) {
                sexData = "女";
            } else {
                sexData = "保密";
            }
        }
        setData();

    }
    @Override
    protected void init() {
        EventBus.getDefault().register(this);

    }

    @Override
    protected void initData() {
        findView();


    }

    @Override
    protected void initView() {
        Log.e("about11111", "initView: ");

    }

    private void setData() {
        introduct.setText(introductData);
        address.setText(addressData);
        sex.setText(sexData);
//        register.setText();
    }


    private void findView() {
        introduct = findViewById(R.id.about_introduct_text);
        address = findViewById(R.id.about_address_text);
        sex = findViewById(R.id.about_sex_text);
//        register = findViewById(R.id.about_registertime_text);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
