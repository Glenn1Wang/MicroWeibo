package com.example.wangguilong.microweibo.ui.activity.ff.follow;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.FriendsBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/5/19.
 */

public class FollowsContract {
    interface IFollowsView {
        Context getcontext();
        void showMsg(String msg);
//        void getFriendsDataSuccess(FriendsBean bean);
        void getFollowsDataSuccess(FriendsBean bean);
    }

    interface IFollowsPresenter {
//        void getFriendsData(String screenName, int cursor);
        void getFollowsData(String screenName,int cursor);
    }

    interface IFollowsModel {
//        void getFriendsData(Context context, String screenName, int cursor, OnHttpCallBack<FriendsBean> callBack);
        void getFollowsData(Context context,String screenName,int cursor,OnHttpCallBack<FriendsBean> callBack);
    }
}
