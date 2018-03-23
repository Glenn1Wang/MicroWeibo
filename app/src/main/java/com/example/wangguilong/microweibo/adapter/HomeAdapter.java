package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.view.likebutton.LikeButton;
import com.example.wangguilong.microweibo.view.likebutton.OnLikeListener;
import com.example.wangguilong.microweibo.widget.WeiBoContentTextUtil;

import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/21.
 */

public class HomeAdapter extends BaseAdapter<TestBean> {
	public HomeAdapter(Context mContext, RecyclerView recyclerView, List<TestBean> mData, int mLayoutId) {
		super(mContext, recyclerView, mData, mLayoutId);
	}

	@Override
	protected void convert(Context mContext, RecyclerView.ViewHolder viewHolder, TestBean bean) {
		if (viewHolder instanceof BaseViewHolder){
//			((BaseViewHolder) viewHolder).getView()
		}
//		viewHolder.text1.setText(WeiBoContentTextUtil.getWeiBoContent(list.get(position).getText(),context,itemHolder.text1));
//
//		viewHolder.userName.setText(list.get(position).getUserName());
//		itemHolder.headCover.setOnClickListener(this);
//		itemHolder.itemView.setOnClickListener(this);
//		itemHolder.repost.setOnClickListener(this);
//		itemHolder.favorite.setOnLikeListener(new OnLikeListener() {
//			@Override
//			public void liked(LikeButton likeButton) {
//				likeButton.setLiked(true);
//			}
//
//			@Override
//			public void unLiked(LikeButton likeButton) {
//				likeButton.setLiked(false);
//			}
//		});
//
//		itemHolder.like.setOnLikeListener(new OnLikeListener() {
//			@Override
//			public void liked(LikeButton likeButton) {
//				likeButton.setLiked(true);
//			}
//
//			@Override
//			public void unLiked(LikeButton likeButton) {
//				likeButton.setLiked(false);
//			}
//		});
	}
}
