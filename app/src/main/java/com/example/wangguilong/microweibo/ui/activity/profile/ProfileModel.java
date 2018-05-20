package com.example.wangguilong.microweibo.ui.activity.profile;

import android.content.Context;
import android.util.Log;

import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;
import com.example.wangguilong.microweibo.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by 77622 on 2018/5/3.
 */

public class ProfileModel implements ProfileContract.IProfileModel {
    @Override
    public void getProfileData(Context context, long uid, final OnHttpCallBack<ProfileBean> callBack) {
        OkGo.<String>get("https://api.weibo.com/2/users/show.json")
                .params("access_token", Util.getAccessToken(context))
                .params("uid",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();


//						if (errorBean !=null) { //出错
//							Log.e("qwer", "onSuccess: "+response.body());
//							callBack.onFail(errorBean.getError());
//						} else {
//							callBack.onFail(errorBean.getError()+":"+errorBean.getError_code());
                        ProfileBean profileBean = gson.fromJson(response.body(), ProfileBean.class);
                        callBack.onSuccess(profileBean);
//



//						}
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.e("qwer", "onError: "+response.body() +response.message()+response.code());
                    }
                });
    }

    @Override
    public void getProfileData(Context context, String screenName, final OnHttpCallBack<ProfileBean> callBack) {
        OkGo.<String>get("https://api.weibo.com/2/users/show.json")
                .params("access_token", Util.getAccessToken(context))
                .params("screen_name",screenName)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();


//						if (errorBean !=null) { //出错
//							Log.e("qwer", "onSuccess: "+response.body());
//							callBack.onFail(errorBean.getError());
//						} else {
//							callBack.onFail(errorBean.getError()+":"+errorBean.getError_code());
                        Log.e("qwer", "profile+onSuccess: "+response.body());
                        ProfileBean profileBean = gson.fromJson(response.body(), ProfileBean.class);
                        callBack.onSuccess(profileBean);
//



//						}
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.e("qwer", "onError: "+response.body() +response.message()+response.code());
                    }
                });
    }
}
