package com.example.wangguilong.microweibo.ui.activity.profile;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/5/3.
 */

public class ProfileContract {
    interface IProfileView {
        Context getcontext();
        void showMsg(String msg);
        void getProfileDataSuccess(ProfileBean bean);
    }

    interface IProfilePresenter {
        void getProfileData(long uid);
        void getProfileData(String screenName);
    }
    interface IProfileModel {
        void getProfileData(Context context,long uid, OnHttpCallBack<ProfileBean> callBack);
        void getProfileData(Context context,String screenName, OnHttpCallBack<ProfileBean> callBack);
    }
}
