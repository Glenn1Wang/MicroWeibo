package com.example.wangguilong.microweibo.ui.fragment.profile.weibo;

import com.example.wangguilong.microweibo.bean.ProfileWeiboBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

import java.util.List;

/**
 * Created by 77622 on 2018/5/14.
 */

public class ProfileWeiboPresenter implements ProfileWeiboContract.IProfileWeiboPresenter {
    private ProfileWeiboContract.IProfileWeiboView iProfileWeiboView;
    private ProfileWeiboContract.IProfileWeiboModel iProfileWeiboModel;

    public ProfileWeiboPresenter(ProfileWeiboContract.IProfileWeiboView iProfileWeiboView) {
        this.iProfileWeiboView = iProfileWeiboView;
        iProfileWeiboModel = new ProfileWeiboModel();
    }

    @Override
    public void getData(String screenName,int page) {
        iProfileWeiboModel.getData(iProfileWeiboView.getcontext(),screenName,page, new OnHttpCallBack<List<ProfileWeiboBean.StatusesBean>>() {
            @Override
            public void onSuccess(List<ProfileWeiboBean.StatusesBean> statusesBeans) {
                iProfileWeiboView.getDataSuccess(statusesBeans);
            }

            @Override
            public void onFail(String msg) {
                iProfileWeiboView.showMsg(msg);
            }
        });
    }
}
