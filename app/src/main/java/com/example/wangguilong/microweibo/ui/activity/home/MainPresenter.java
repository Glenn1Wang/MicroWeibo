package com.example.wangguilong.microweibo.ui.activity.home;

import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/4/23.
 */

public class MainPresenter implements MainContract.IMainPresenter {
    private MainContract.IMainModel iMainModel;
    private MainContract.IMainView iMainView;

    public MainPresenter(MainContract.IMainView iMainView) {
        this.iMainView = iMainView;
        iMainModel = new MainModel();
    }
    @Override
    public void getProfileData(long uid) {
            iMainModel.getProfileData(iMainView.getcontext(), uid, new OnHttpCallBack<ProfileBean>() {
                @Override
                public void onSuccess(ProfileBean bean) {
                    iMainView.getProfileDataSuccess(bean);
                }

                @Override
                public void onFail(String msg) {
                    iMainView.showMsg(msg);
                }
            });
    }
}
