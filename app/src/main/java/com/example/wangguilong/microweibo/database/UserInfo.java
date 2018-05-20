package com.example.wangguilong.microweibo.database;

import org.litepal.crud.DataSupport;

/**
 * Created by 77622 on 2018/5/20.
 */

public class UserInfo extends DataSupport{
    private int id;
    private long uid;
    private String screenName;
    private String accessToken;
    private boolean current;

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
