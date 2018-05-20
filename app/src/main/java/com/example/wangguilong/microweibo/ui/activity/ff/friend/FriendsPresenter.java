package com.example.wangguilong.microweibo.ui.activity.ff.friend;

import com.example.wangguilong.microweibo.bean.FriendsBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/5/19.
 */

public class FriendsPresenter implements FriendsContract.IFriendsPresenter {
    private FriendsContract.IFriendsView iFriendsView;
    private FriendsContract.IFriendsModel iFriendsModel;

    public FriendsPresenter(FriendsContract.IFriendsView iFriendsView) {
        this.iFriendsView = iFriendsView;
        iFriendsModel = new FriendsModel();
    }

    @Override
    public void getFriendsData(String screenName,int cursor) {
        iFriendsModel.getFriendsData(iFriendsView.getcontext(),screenName,cursor, new OnHttpCallBack<FriendsBean>() {
            @Override
            public void onSuccess(FriendsBean friendsBean) {
                iFriendsView.getFriendsDataSuccess(friendsBean);
            }

            @Override
            public void onFail(String msg) {
                iFriendsView.showMsg(msg);
            }
        });
    }
//
//    @Override
//    public void getFollowsData(String screenName, int cursor) {
//        iFriendsModel.getFollowsData(iFriendsView.getcontext(), screenName, cursor, new OnHttpCallBack<FriendsBean>() {
//            @Override
//            public void onSuccess(FriendsBean bean) {
//                iFriendsView.getFollowsDataSuccess(bean);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                iFriendsView.showMsg(msg);
//            }
//        });
//    }
}
