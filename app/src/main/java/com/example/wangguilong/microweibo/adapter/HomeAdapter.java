package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.ui.activity.profile.ProfileActivity;
import com.example.wangguilong.microweibo.util.Util;
import com.example.wangguilong.microweibo.view.gridview.MyGridView;
import com.example.wangguilong.microweibo.view.likebutton.LikeButton;
import com.example.wangguilong.microweibo.widget.WeiBoContentTextUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/21.
 */

public class HomeAdapter extends BaseAdapter<TestBean.StatusesBean> {
	public HomeAdapter(Context mContext, RecyclerView recyclerView, List<TestBean.StatusesBean> mData, int mLayoutId) {
		super(mContext, recyclerView, mData, mLayoutId);
	}
//	public HomeAdapter(Context mContext, RecyclerView recyclerView, List<TestBean> mData, int mLayoutId) {
//		super(mContext, recyclerView, mData, mLayoutId);
//	}
//
//	@Override
//	protected void convert(Context mContext, RecyclerView.ViewHolder viewHolder, TestBean bean) {
//		if (viewHolder instanceof BaseViewHolder){
//			((BaseViewHolder) viewHolder).setText(R.id.user_name,bean.getStatuses().get(1).getUser().get);
//		}
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
//	}

	@Override
	protected void convert(Context mContext, RecyclerView.ViewHolder viewHolder, TestBean.StatusesBean statusesBean) {
		if (viewHolder instanceof BaseViewHolder){
			BaseViewHolder holder = (BaseViewHolder) viewHolder;

			LinearLayout linearLayout = holder.getView(R.id.retweeted_linear);
			linearLayout.setVisibility(View.GONE);
			//姓名
			holder.setText(R.id.user_name, statusesBean.getUser().getName());
			//头像
			holder.setImage(mContext,statusesBean.getUser().getProfile_image_url(),R.id.profile_image);
			//内容
			TextView text = holder.getView(R.id.text);
			String oriText = statusesBean.getText();
			if (oriText.length() >140) {
				oriText = oriText.substring(0,140).concat("...展开全文");
			}
			SpannableStringBuilder textString = WeiBoContentTextUtil.getWeiBoContent(oriText, mContext, text);
			String created_at = statusesBean.getCreated_at();
			String s = Util.toTime(Util.toTimestamp(created_at));
			holder.setText(R.id.time_text,s);
//			Log.e("getOriginal_pic", "create: " +created_at +"-------format"+ Util.toTimestamp(created_at));
			text.setText(textString);
			//转发
			holder.setText(R.id.report_count_text,statusesBean.getReposts_count()+"");
			//评论
			holder.setText(R.id.comment_count_text,statusesBean.getComments_count()+"");
			//点赞
			holder.setText(R.id.like_count_text,statusesBean.getAttitudes_count()+"");
			//是否已收藏
			if (statusesBean.isFavorited()) {
				LikeButton favorite = holder.getView(R.id.favorite);
				favorite.setLiked(true);
			}
			if (statusesBean.getOriginal_pic()!=null) {
				Log.e("getOriginal_pic", "convert: "+"name"+statusesBean.getUser().getName()+statusesBean.getOriginal_pic());
			}
			if (statusesBean.getRetweeted_status() != null) {

				linearLayout.setVisibility(View.VISIBLE);
				holder.setText(R.id.retweeted_name_text,statusesBean.getRetweeted_status().getUser().getScreen_name() + ":");
//				Log.e("adapter","convert: "+statusesBean.getUser().getName() +" -------" + statusesBean.getRetweeted_status().getUser().getScreen_name()+"-------------" +statusesBean.getRetweeted_status().getText() );
				TextView text1 = holder.getView(R.id.retweeted_text_text);
				SpannableStringBuilder textString1 = WeiBoContentTextUtil.getWeiBoContent(statusesBean.getRetweeted_status().getText(), mContext, text1);
				text1.setText(textString1);

			}
			if (statusesBean.getPic_urls() != null){
//				List<String> images = new ArrayList<>();

				List<String> pic_urls = new ArrayList<>();
				pic_urls.clear();
				if (statusesBean.getPic_urls().size() == 1) {
					pic_urls.add(statusesBean.getOriginal_pic());
				} else {
					for (int i=0;i<statusesBean.getPic_urls().size();i++) {
						pic_urls.add(statusesBean.getPic_urls().get(i).getThumbnail_pic());
					}
				}

//				pic_urls.addAll();

//				Log.e("picurl", "convert: "+pic_urls.get(0).getThumbnail_pic());
				MyGridView gridView = holder.getView(R.id.gridview);
				gridView.setVisibility(View.VISIBLE);
//				if (pic_urls.size() == 1) {
//					gridView.setNumColumns(1);
//				}

				PicAdapter adapter = new PicAdapter(mContext,pic_urls);
				gridView.setAdapter(adapter);
			}
			setListener(mContext,holder,statusesBean);
		}
	}

	private void setListener(final Context context, BaseViewHolder holder, final TestBean.StatusesBean bean) {
		holder.setOnClickListener(R.id.profile_image, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent= new Intent();
				intent.setClass(context, ProfileActivity.class);
				intent.putExtra("screen_name",bean.getUser().getScreen_name());
				context.startActivity(intent);
			}
		});

		final TextView likeCount = holder.getView(R.id.like_count_text);
		final LikeButton like = holder.getView(R.id.like);

		holder.setOnClickListener(R.id.like, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!like.isLiked()) {
					like.setLiked(true);
					likeCount.post(new Runnable() {
						@Override
						public void run() {
							likeCount.setText(Integer.valueOf(likeCount.getText().toString())+1 + "");
						}
					});
				} else if (like.isLiked() && Integer.valueOf(likeCount.getText().toString())>0){
					like.setLiked(false);
					likeCount.post(new Runnable() {
						@Override
						public void run() {
							likeCount.setText(Integer.valueOf(likeCount.getText().toString())-1 + "");
						}
					});
				}

			}
		});
	}
}
