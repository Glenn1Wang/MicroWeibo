package com.example.wangguilong.microweibo.ui.activity.ff.friend;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.FriendsBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;

/**
 * Created by 77622 on 2018/5/19.
 */

public class FriendsContract {
    interface IFriendsView {
        Context getcontext();
        void showMsg(String msg);
        void getFriendsDataSuccess(FriendsBean bean);
//        void getFollowsDataSuccess(FriendsBean bean);
    }

    interface IFriendsPresenter {
        void getFriendsData(String screenName,int cursor);
//        void getFollowsData(String screenName,int cursor);
    }

    interface IFriendsModel {
        void getFriendsData(Context context,String screenName,int cursor,OnHttpCallBack<FriendsBean> callBack);
//        void getFollowsData(Context context,String screenName,int cursor,OnHttpCallBack<FriendsBean> callBack);
    }
}
