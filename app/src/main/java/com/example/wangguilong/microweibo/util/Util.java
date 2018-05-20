package com.example.wangguilong.microweibo.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static long getUID(Context context) {
        return context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getLong("uid",0);

    }

    public static String getScreenName(Context context) {
        return context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getString("screen_name",null);

    }

    public static long toTimestamp(String time){
        Date date = new Date(time);
        return date.getTime();
//        String s = String.valueOf(date.getTime());
//        String res = null;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
//        Date date = null;
//        try {
//            date = simpleDateFormat.parse(time);
//            long ts = date.getTime();
//            res = String.valueOf(ts);
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return s;
        
    }

    public static String toTime(long timeStamp) {
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd HH:mm");//这个是你要转成后的时间的格式
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(timeStamp))));   // 时间戳转换成时间
        return sd;
    }


    public static byte[] getImageFromLocalByUrl(String strUrl) {
        try {
            File imageFile = new File(strUrl);
            InputStream inStream = new FileInputStream(imageFile);
            byte[] btImg = readInputStream(inStream);// 得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[10240];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();

    }


}
