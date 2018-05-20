package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.bean.FriendsBean;

import java.util.List;

/**
 * Created by 77622 on 2018/5/19.
 */

public class FriendsAdapter extends BaseAdapter<FriendsBean.UsersBean> {
    public FriendsAdapter(Context mContext, RecyclerView recyclerView, List<FriendsBean.UsersBean> mData, int mLayoutId) {
        super(mContext, recyclerView, mData, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, RecyclerView.ViewHolder viewHolder, FriendsBean.UsersBean usersBean) {
        if (viewHolder instanceof BaseViewHolder) {
            BaseViewHolder holder = (BaseViewHolder) viewHolder;
            //头像
            holder.setImage(mContext,usersBean.getAvatar_large(), R.id.head_image);
            //名字
            holder.setText(R.id.friends_name_text,usersBean.getScreen_name());
            //描述
            holder.setText(R.id.friends_description_text,usersBean.getDescription());
            //follow me
            if (usersBean.isFollow_me()) {
                holder.setImageResource(R.id.friends_follow_image,R.drawable.icon_follow2);
                holder.setText(R.id.friends_follow_text,"互相关注");
            } else {
                holder.setImageResource(R.id.friends_follow_image,R.drawable.icon_follow1);
                holder.setText(R.id.friends_follow_text,"已关注");
            }

        }
    }
}
