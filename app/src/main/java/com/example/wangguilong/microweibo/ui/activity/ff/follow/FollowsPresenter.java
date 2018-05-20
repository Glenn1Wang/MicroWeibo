package com.example.wangguilong.microweibo.ui.activity.ff.follow;

import com.example.wangguilong.microweibo.bean.FriendsBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/5/19.
 */

public class FollowsPresenter implements FollowsContract.IFollowsPresenter {
    private FollowsContract.IFollowsView iFollowsView;
    private FollowsContract.IFollowsModel iFollowsModel;

    public FollowsPresenter(FollowsContract.IFollowsView iFollowsView) {
        this.iFollowsView = iFollowsView;
        iFollowsModel = new FollowsModel();
    }

//    @Override
//    public void getFriendsData(String screenName,int cursor) {
//        iFriendsModel.getFriendsData(iFriendsView.getcontext(),screenName,cursor, new OnHttpCallBack<FriendsBean>() {
//            @Override
//            public void onSuccess(FriendsBean friendsBean) {
//                iFriendsView.getFriendsDataSuccess(friendsBean);
//            }
//
//            @Override
//            public void onFail(String msg) {
//                iFriendsView.showMsg(msg);
//            }
//        });
//    }
//
    @Override
    public void getFollowsData(String screenName, int cursor) {
        iFollowsModel.getFollowsData(iFollowsView.getcontext(), screenName, cursor, new OnHttpCallBack<FriendsBean>() {
            @Override
            public void onSuccess(FriendsBean bean) {
                iFollowsView.getFollowsDataSuccess(bean);
            }

            @Override
            public void onFail(String msg) {
                iFollowsView.showMsg(msg);
            }
        });
    }
}
