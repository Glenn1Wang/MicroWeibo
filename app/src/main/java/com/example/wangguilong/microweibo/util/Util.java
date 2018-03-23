package com.example.wangguilong.microweibo.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.File;
import java.util.Calendar;

/**
 * 工具类
 * Created by cheng on 2018/1/10.
 */

public class Util {
    public final static int LOGINSUCCESS=0;  //登录成功返回码

    /**
     * 显示吐司
     */
    public static void t(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取屏幕高度 像素
     * @return
     */
    public static int getScreenHeight(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度 像素
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH)+1;
    }


    /**
     * 获取当前日
     * @return
     */
    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static String getAccessToken(Context context) {
        return context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString("access_token",null);
    }

}
