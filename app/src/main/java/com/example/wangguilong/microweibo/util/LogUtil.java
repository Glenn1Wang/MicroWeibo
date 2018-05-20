package com.example.wangguilong.microweibo.util;

import android.util.Log;

/**
 * Created by WangGuiLong on 2018/3/17.
 */

public class LogUtil {

	//可以全局控制是否打印log日志
	private static boolean isPrintLog = true;
	private static int LOG_MAXLENGTH = 2000;


	public static void v(String msg) {
		v("LogUtil", msg);
	}

	public static void v(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.v(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.v(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}

	public static void d(String msg) {
		d("LogUtil", msg);
	}
	public static void d(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.d(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.d(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}

	public static void i(String msg) {
		i("LogUtil", msg);
	}

	public static void i(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.i(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.i(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}

	public static void w(String msg) {
		w("LogUtil", msg);
	}

	public static void w(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.w(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.w(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}

	public static void e(String msg) {
		e("LogUtil", msg);
	}
	public static void e(String tagName, String msg) {
		if (isPrintLog) {
			int strLength = msg.length();
			int start = 0;
			int end = LOG_MAXLENGTH;
			for (int i = 0; i < 100; i++) {
				if (strLength > end) {
					Log.e(tagName + i, msg.substring(start, end));
					start = end;
					end = end + LOG_MAXLENGTH;
				} else {
					Log.e(tagName + i, msg.substring(start, strLength));
					break;
				}
			}
		}
	}

	public static void ee(String response) {
		if (response.length() > 3900) {
			for (int i = 0; i < response.length(); i += 3900) {
				if (i + 3900 < response.length()) {
					Log.e("第" + i + "数据", response.substring(i, i + 3900));
				} else {
					Log.e("第" + i + "数据", response.substring(i, response.length()));
				}
			}
		} else {
			Log.e("全部数据", "************************  response = " + response);
		}
	}

	public static void ee(String tag,String response) {
		if (response.length() > 3900) {
			for (int i = 0; i < response.length(); i += 3900) {
				if (i + 3900 < response.length()) {
					Log.e(tag+"===========第" + i + "数据", response.substring(i, i + 3900));
				} else {
					Log.e(tag+"===========第" + i + "数据", response.substring(i, response.length()));
				}
			}
		} else {
			Log.e(tag+"===========全部数据", "************************  response = " + response);
		}
	}

}
