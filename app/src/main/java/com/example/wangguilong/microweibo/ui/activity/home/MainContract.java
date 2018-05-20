package com.example.wangguilong.microweibo.ui.activity.home;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/4/23.
 */

public class MainContract {

    interface IMainView {
        Context getcontext();
        void showMsg(String msg);
        void getProfileDataSuccess(ProfileBean bean);
    }

    interface IMainPresenter {
        void getProfileData(long uid);
    }

    interface IMainModel {
        void getProfileData(Context context, long uid,OnHttpCallBack<ProfileBean> callBack);
    }
}
