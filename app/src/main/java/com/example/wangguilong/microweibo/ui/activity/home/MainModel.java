package com.example.wangguilong.microweibo.ui.activity.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.wangguilong.microweibo.bean.ProfileBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;
import com.example.wangguilong.microweibo.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 77622 on 2018/4/23.
 */

public class MainModel implements MainContract.IMainModel {
    @Override
    public void getProfileData(final Context context, long uid, final OnHttpCallBack<ProfileBean> callBack) {
        OkGo.<String>get("https://api.weibo.com/2/users/show.json")
                .params("access_token", Util.getAccessToken(context))
                .params("uid",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        Log.e("main", "onSuccess: "+response.body());
                        SharedPreferences user_info = context.getSharedPreferences("user_info", MODE_PRIVATE);
                        SharedPreferences.Editor edit = user_info.edit();

//						if (errorBean !=null) { //出错
//							Log.e("qwer", "onSuccess: "+response.body());
//							callBack.onFail(errorBean.getError());
//						} else {
//							callBack.onFail(errorBean.getError()+":"+errorBean.getError_code());
//                        Log.e("qwer", "profile+onSuccess: "+response.body());
                        ProfileBean profileBean = gson.fromJson(response.body(), ProfileBean.class);
                        edit.putString("screen_name",profileBean.getScreen_name());
                        edit.commit();
                        callBack.onSuccess(profileBean);
//


//						}
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        Log.e("qwer", "onError: "+response.body() +response.message()+response.code());
                    }
                });
    }
}
