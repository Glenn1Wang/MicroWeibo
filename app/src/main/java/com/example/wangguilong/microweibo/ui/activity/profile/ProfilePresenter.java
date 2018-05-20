package com.example.wangguilong.microweibo.ui.activity.profile;

import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/5/3.
 */

public class ProfilePresenter implements ProfileContract.IProfilePresenter {
    private ProfileContract.IProfileView iProfileView;
    private ProfileContract.IProfileModel iProfileModel;

    public ProfilePresenter(ProfileContract.IProfileView iProfileView) {
        this.iProfileView = iProfileView;
        iProfileModel = new ProfileModel();
    }


    @Override
    public void getProfileData(long uid) {
        iProfileModel.getProfileData(iProfileView.getcontext(), uid, new OnHttpCallBack<ProfileBean>() {
            @Override
            public void onSuccess(ProfileBean bean) {
                iProfileView.getProfileDataSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                iProfileView.showMsg(msg);
            }
        });
    }

    @Override
    public void getProfileData(String screenName) {
        iProfileModel.getProfileData(iProfileView.getcontext(), screenName, new OnHttpCallBack<ProfileBean>() {
            @Override
            public void onSuccess(ProfileBean bean) {
                iProfileView.getProfileDataSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                iProfileView.showMsg(msg);
            }
        });
    }
}
