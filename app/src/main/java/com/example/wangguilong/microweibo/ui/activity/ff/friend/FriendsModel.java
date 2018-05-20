package com.example.wangguilong.microweibo.ui.activity.ff.friend;

import android.content.Context;
import android.util.Log;

import com.example.wangguilong.microweibo.bean.ErrorBean;
import com.example.wangguilong.microweibo.bean.FriendsBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;
import com.example.wangguilong.microweibo.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by 77622 on 2018/5/19.
 */

public class FriendsModel implements FriendsContract.IFriendsModel {
    //https://api.weibo.com/2/friendships/followers.json
    @Override
    public void getFriendsData(Context context, String screenName, int cursor, final OnHttpCallBack<FriendsBean> callBack) {
        OkGo.<String>get("https://api.weibo.com/2/friendships/friends.json")
                .params("access_token", Util.getAccessToken(context))
//				.params("count",3)
                .params("screen_name",screenName)
                .params("cursor",cursor)
//				.params("feature",2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        Gson gson1 = new Gson();
                        ErrorBean errorBean = gson.fromJson(response.body(), ErrorBean.class);
                        FriendsBean bean = gson1.fromJson(response.body(), FriendsBean.class);
                        callBack.onSuccess(bean);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.e("qwer", "onError: "+response.body() +response.message()+response.code());
                    }
                });
    }

//    @Override
//    public void getFollowsData(Context context, String screenName, int cursor, final OnHttpCallBack<FriendsBean> callBack) {
//        OkGo.<String>get("https://api.weibo.com/2/friendships/followers.json")
//                .params("access_token", Util.getAccessToken(context))
////				.params("count",3)
//                .params("screen_name",screenName)
//                .params("cursor",cursor)
////				.params("feature",2)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        Gson gson = new Gson();
//                        Gson gson1 = new Gson();
//                        ErrorBean errorBean = gson.fromJson(response.body(), ErrorBean.class);
//                        FriendsBean bean = gson1.fromJson(response.body(), FriendsBean.class);
//                        callBack.onSuccess(bean);
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//                        super.onError(response);
//                        Log.e("qwer", "onError: "+response.body() +response.message()+response.code());
//                    }
//                });
//    }
}
