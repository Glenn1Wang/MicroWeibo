package com.example.wangguilong.microweibo.ui.fragment.profile.weibo;

import android.content.Context;

import com.example.wangguilong.microweibo.bean.ErrorBean;
import com.example.wangguilong.microweibo.bean.ProfileWeiboBean;
import com.example.wangguilong.microweibo.callback.OnHttpCallBack;
import com.example.wangguilong.microweibo.util.LogUtil;
import com.example.wangguilong.microweibo.util.Util;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by 77622 on 2018/5/14.
 */

public class ProfileWeiboModel implements ProfileWeiboContract.IProfileWeiboModel {
    @Override
    public void getData(Context context,String screenName,int page, final OnHttpCallBack<List<ProfileWeiboBean.StatusesBean>> callBack) {
        OkGo.<String>get("https://api.weibo.com/2/statuses/user_timeline.json")
                .params("access_token", Util.getAccessToken(context))
//				.params("count",1)
                .params("screen_name",screenName)
                .params("page",page)
//				.params("feature",2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        Gson gson1 = new Gson();
                        ErrorBean errorBean = gson.fromJson(response.body(), ErrorBean.class);
//						if (errorBean !=null) { //出错
//							Log.e("qwer", "onSuccess: "+response.body());
//							callBack.onFail(errorBean.getError());
//						} else {
//							callBack.onFail(errorBean.getError()+":"+errorBean.getError_code());
                        LogUtil.ee(response.body());
                        ProfileWeiboBean bean = gson1.fromJson(response.body(), ProfileWeiboBean.class);
                        callBack.onSuccess(bean.getStatuses());

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
