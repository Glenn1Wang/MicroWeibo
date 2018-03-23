package com.example.wangguilong.microweibo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by WangGuiLong on 2018/3/19.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
	SparseArray<View> mViews;
	View mItemView;

	public BaseViewHolder(View itemView) {
		super(itemView);
		mItemView = itemView;
		mViews = new SparseArray<>();
	}

	/**
	 * 获取控件对象
	 * @param viewId
	 * @param <T>
	 * @return
	 */
	public <T extends View> T getView(int viewId) {
		View view = mItemView.findViewById(viewId);
		if (view == null) {
			view = mItemView.findViewById(viewId);
			mViews.put(viewId,view);
		}
		return (T) view;
	}

	public BaseViewHolder setText(int viewId, String text) {
		TextView textView = getView(viewId);
		textView.setText(text);
		return this;
	}

	public BaseViewHolder setText(int viewId, SpannableStringBuilder text) {
		TextView textView = getView(viewId);
		textView.setText(text);
		return this;
	}

	public BaseViewHolder setImage(Context context, String uri,int resId) {
		Glide.with(context).load(uri).into((ImageView) getView(resId));
		return this;
	}

	public BaseViewHolder setImageResource(int viewId, int resId) {
		ImageView view = getView(viewId);
		view.setImageResource(resId);
		return this;
	}
	public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
		ImageView view = getView(viewId);
		view.setImageBitmap(bitmap);
		return this;
	}
	public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {
		ImageView view = getView(viewId);
		view.setImageDrawable(drawable);
		return this;
	}
	public BaseViewHolder setBackgroundColor(int viewId, int color) {
		View view = getView(viewId);
		view.setBackgroundColor(color);
		return this;
	}
	public BaseViewHolder setBackgroundResource(int viewId, int backgroundRes) {
		View view = getView(viewId);
		view.setBackgroundResource(backgroundRes);
		return this;
	}
	public BaseViewHolder setTextColor(int viewId, int textColor) {
		TextView view = getView(viewId);
		view.setTextColor(textColor);
		return this;
	}
	@SuppressLint("NewApi")
	public BaseViewHolder setAlpha(int viewId, float value) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getView(viewId).setAlpha(value);
		} else {
			// Pre-honeycomb hack to set Alpha value
			AlphaAnimation alpha = new AlphaAnimation(value, value);
			alpha.setDuration(0);
			alpha.setFillAfter(true);
			getView(viewId).startAnimation(alpha);
		}
		return this;
	}
	public BaseViewHolder setVisible(int viewId, boolean visible) {
		View view = getView(viewId);
		view.setVisibility(visible ? View.VISIBLE : View.GONE);
		return this;
	}
	public BaseViewHolder setTag(int viewId, Object tag) {
		View view = getView(viewId);
		view.setTag(tag);
		return this;
	}
	public BaseViewHolder setTag(int viewId, int key, Object tag) {
		View view = getView(viewId);
		view.setTag(key, tag);
		return this;
	}
	public BaseViewHolder setChecked(int viewId, boolean checked) {
		Checkable view = (Checkable) getView(viewId);
		view.setChecked(checked);
		return this;
	}

	/**
	 * 控件的点击监听
	 * @param viewId
	 * @param listener
	 * @return
	 */
	public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
		getView(viewId).setOnClickListener(listener);
		return this;
	}

	/**
	 * 控件的长按监听
	 * @param viewId
	 * @param listener
	 * @return
	 */
	public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
		getView(viewId).setOnLongClickListener(listener);
		return this;
	}
}
