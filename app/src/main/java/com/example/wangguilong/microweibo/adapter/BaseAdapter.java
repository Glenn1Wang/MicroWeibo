package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.wangguilong.microweibo.R;

import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/19.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter{
	private static final int TYPE_HEAD = 0720;
	private static final int TYPE_ITEM = 0721;
	private static final int TYPE_PROGRESS = 0722;

	private Context mContext;
	private List<T> mData;
	private int mLayoutId;
	private OnItemClickListener mItemClickListener;
	private OnItemLongClickListener mItemLongClickListener;
	private OnLoadMoreListener mLoadMoreListener;
	private View mHeadView;
	private boolean isLoading = false;


	public BaseAdapter(Context mContext,RecyclerView recyclerView, List<T> mData, int mLayoutId) {
		this.mContext = mContext;
		this.mData = mData;
		this.mLayoutId = mLayoutId;

		//初始化recyclerview
		init(recyclerView);
	}

	private void init(RecyclerView recyclerView) {
		recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
				//全部的item个数
				int totalItemCount = manager.getItemCount();
				//最后可见的item的位置
				int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
				if (!isLoading && dy>0 && lastVisibleItemPosition >=  totalItemCount - 1) {
					//
					if (mLoadMoreListener != null) {
						mLoadMoreListener.onLoadMore();
					}
					isLoading = true;
				}

			}
		});
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == TYPE_ITEM) {
			View itemView = LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
			BaseViewHolder holder = new BaseViewHolder(itemView);
			return holder;
		}else if (viewType == TYPE_HEAD) {
			HeadViewHolder holder = new HeadViewHolder(mHeadView);
			return holder;
		} else {
			View progressView = LayoutInflater.from(mContext).inflate(R.layout.progress_item,parent,false);
			//开启加载更多动画
			ImageView imageView = progressView.findViewById(R.id.loading_more_image);
			AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
			animationDrawable.start();
			return new ProgressViewHolder(progressView);
		}
	}


	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		if (holder instanceof BaseViewHolder) {
			convert(mContext,holder,mData.get(position));
			((BaseViewHolder) holder).mItemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					//把item的监听传入自己写的接口中
					mItemClickListener.onItemClick(view,position-1);
				}
			});
			((BaseViewHolder) holder).mItemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					mItemLongClickListener.onItemLongClick(view,position-1);
					return true;
				}
			});
		}
	}

	@Override
	public int getItemViewType(int position) {
		if (mHeadView !=null) {
			if (position == getItemCount() - 1) {
				return TYPE_PROGRESS;
			} else if (position == 0) {
				return TYPE_HEAD;
			} else {
				return TYPE_ITEM;
			}
		} else {
			if (position == getItemCount() - 1) {
				return TYPE_PROGRESS;
			} else {
				return TYPE_ITEM;
			}
		}
	}

	@Override
	public int getItemCount() {
		return mData.size()+1;
	}

	public void setLoading(boolean loading) {
		isLoading = loading;
	}

	public boolean getLoading() {
		return isLoading;
	}

	public void addHeadView(View headView) {
		mHeadView = headView;
	}

	/**
	 * 更新所有数据
	 * @param data
	 */
	public void updateData(List<T> data) {
		mData.clear();
		mData.addAll(data);
		notifyDataSetChanged();
	}

	/**
	 * 添加所有数据
	 * @param data
	 */
	public void addAll(List<T> data) {
		mData.addAll(data);
		notifyDataSetChanged();
	}

	/**
	 *
	 * @param mContext
	 * @param viewHolder
	 * @param t
	 */
	protected abstract void convert(Context mContext, RecyclerView.ViewHolder viewHolder, T t);


	/**
	 * item点击监听接口
	 */
	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	/**
	 * item长按监听接口
	 */
	public interface OnItemLongClickListener {
		void onItemLongClick(View view, int position);
	}

	/**
	 * 上拉加载更多 监听接口
	 */
	public interface OnLoadMoreListener {
		void onLoadMore();
	}

	/**
	 * item点击  暴露在外的方法
	 * @param listener
	 */
	public void setOnItemClickListener(OnItemClickListener listener){
		this.mItemClickListener = listener;
	}

	/**
	 * item长按   暴露在外的方法
	 * @param listener
	 */
	public void setOnItemLongClickListener(OnItemLongClickListener listener){
		this.mItemLongClickListener = listener;
	}

	/**
	 * 上拉加载更多   暴露在外的方法
	 * @param listener
	 */
	public void setOnLoadMoreClickListener(OnLoadMoreListener listener){
		this.mLoadMoreListener = listener;
	}


	public class ProgressViewHolder extends RecyclerView.ViewHolder{
		public ProgressViewHolder(View itemView) {
			super(itemView);
		}
	}

	public class HeadViewHolder extends RecyclerView.ViewHolder{
		public HeadViewHolder(View itemView) {
			super(itemView);
		}
	}
}
