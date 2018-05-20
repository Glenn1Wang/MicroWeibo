package com.example.wangguilong.microweibo.ui.fragment.profile.weibo;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.ProfileWeiboBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

import java.util.List;

/**
 * Created by 77622 on 2018/5/14.
 */

public class ProfileWeiboContract {
    interface IProfileWeiboView {
        Context getcontext();
        void showMsg(String msg);
        void getDataSuccess(List<ProfileWeiboBean.StatusesBean> bean);
    }

    interface IProfileWeiboPresenter {
        void getData(String screenName,int page);
    }

    interface IProfileWeiboModel {
        void getData(Context context,String screenName,int page,OnHttpCallBack<List<ProfileWeiboBean.StatusesBean>> callBack);
    }
}
