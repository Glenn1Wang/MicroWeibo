package com.example.wangguilong.microweibo.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.wangguilong.microweibo.util.Util;


/**
 * Created by WangGuiLong on 2018/1/30.
 */

public class CustomDialog extends Dialog {
	private Context context;
	private int height,width;
	private boolean touchOutToCancel;
	private View view;
	private int gravity;

	public CustomDialog(Builder builder) {
		super(builder.context);
		context = builder.context;
		height = builder.height;
		width = builder.width;
		touchOutToCancel = builder.touchOutToCancel;
		view = builder.view;
		gravity = builder.gravity;
	}

	public CustomDialog(Builder builder, int themeResId) {
		super(builder.context, themeResId);
		context = builder.context;
		height = builder.height;
		width = builder.width;
		touchOutToCancel = builder.touchOutToCancel;
		view = builder.view;
		gravity = builder.gravity;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(view);
		setCanceledOnTouchOutside(touchOutToCancel);

		//控制dialog显示位置 宽高
		Window window = getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.gravity = gravity;
		lp.height = height;
		lp.width = width;
		window.setAttributes(lp);
	}

	public static final class Builder {
		private Context context;
		private int height,width;
		private boolean touchOutToCancel;
		private View view;
		private int resStyle = -1;
		private int gravity = Gravity.CENTER; //默认居中

		public Builder(Context context) {
			this.context = context;
			width = Util.getScreenWidth(context);
		}

		public Builder view (int resView) {
			view = LayoutInflater.from(context).inflate(resView,null);
			return this;
		}

		public Builder heightPx(int val) {
			height = val;
			return this;
		}
		public Builder widthPx(int val) {
			width = val;
			return this;
		}

		public Builder heightDp(int val) {
			height = Util.dip2px(context, val);
			return this;
		}

		public Builder widthDp(int val) {
			width = Util.dip2px(context, val);
			return this;
		}

		public Builder heightDimenRes(int dimenRes) {
			height = context.getResources().getDimensionPixelOffset(dimenRes);
			return this;
		}

		public Builder widthDimenRes(int dimenRes) {
			width = context.getResources().getDimensionPixelOffset(dimenRes);
			return this;
		}

		public Builder setGravity(int gravity) {
			this.gravity = gravity;
			return this;
		}
		public Builder style(int resStyle) {
			this.resStyle = resStyle;
			return this;
		}

		public Builder touchOutToCancel(boolean val) {
			touchOutToCancel = val;
			return this;
		}

		//添加控件的点击监听
		public Builder addViewOnclick(int viewRes,View.OnClickListener listener){
			view.findViewById(viewRes).setOnClickListener(listener);
			return this;
		}

		/**
		 * 获取dialog的布局对象 以便对布局内的控件进行操作设置
		 * @param listener
		 * @return
		 */
		public Builder setViewAttr(IGetViewListener listener) {
			if (view != null) {
				listener.getView(view);
			} else {
				throw new RuntimeException("view cannot be null");
			}
			return this;
		}


		public CustomDialog build() {
			if (resStyle != -1) {
				return new CustomDialog(this, resStyle);
			} else {
				return new CustomDialog(this);
			}
		}

	}

	public interface IGetViewListener {
		void getView(View view);
	}

}
