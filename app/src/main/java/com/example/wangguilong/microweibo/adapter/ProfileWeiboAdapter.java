package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.bean.ProfileWeiboBean;
import com.example.wangguilong.microweibo.widget.WeiBoContentTextUtil;

import java.util.List;

/**
 * Created by 77622 on 2018/5/14.
 */

public class ProfileWeiboAdapter extends BaseAdapter<ProfileWeiboBean.StatusesBean> {
    public ProfileWeiboAdapter(Context mContext, RecyclerView recyclerView, List<ProfileWeiboBean.StatusesBean> mData, int mLayoutId) {
        super(mContext, recyclerView, mData, mLayoutId);
    }

    @Override
    protected void convert(Context mContext, RecyclerView.ViewHolder viewHolder, ProfileWeiboBean.StatusesBean statusesBean) {
        if (viewHolder instanceof BaseViewHolder){
            if (statusesBean!= null) {
                ((BaseViewHolder) viewHolder).setText(R.id.user_name, statusesBean.getUser().getName());
                ((BaseViewHolder) viewHolder).setImage(mContext,statusesBean.getUser().getProfile_image_url(),R.id.profile_image);
                ((BaseViewHolder) viewHolder).setText(R.id.text, WeiBoContentTextUtil.getWeiBoContent(statusesBean.getText(),mContext,(TextView)(((BaseViewHolder) viewHolder).getView(R.id.text))));
            }

        }
    }
}
