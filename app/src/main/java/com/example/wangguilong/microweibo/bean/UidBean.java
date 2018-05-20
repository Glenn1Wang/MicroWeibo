package com.example.wangguilong.microweibo.bean;

import com.google.gson.Gson;

/**
 * Created by 77622 on 2018/5/15.
 */

public class UidBean {

    /**
     * uid : 1791494471
     */

    private long uid;

    public static UidBean objectFromData(String str) {

        return new Gson().fromJson(str, UidBean.class);
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
