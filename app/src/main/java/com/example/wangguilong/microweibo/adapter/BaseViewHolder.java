package com.example.wangguilong.microweibo.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

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
