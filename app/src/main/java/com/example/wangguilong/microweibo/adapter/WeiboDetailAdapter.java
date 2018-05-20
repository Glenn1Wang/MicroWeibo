package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/19.
 */

public class WeiboDetailAdapter extends BaseAdapter{
	public WeiboDetailAdapter(Context mContext, RecyclerView recyclerView, List mData, int mLayoutId) {
		super(mContext, recyclerView, mData, mLayoutId);
	}

	@Override
	protected void convert(Context mContext, RecyclerView.ViewHolder viewHolder, Object o) {
		if (viewHolder instanceof BaseViewHolder) {
			BaseViewHolder holder = (BaseViewHolder) viewHolder;

		}
	}
}
