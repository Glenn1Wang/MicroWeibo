package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77622 on 2018/5/18.
 */

public class PicAdapter extends BaseAdapter {
    private Context context;
    private List<String> images = new ArrayList<>();

    public PicAdapter(Context context, List<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridview_item_layout, parent, false);
        ImageView imageView = view.findViewById(R.id.gridview_item_image);
        if (images.size() == 1) {
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            imageView.getLayoutParams().height = Util.dip2px(context,200);
            imageView.getLayoutParams().width = Util.dip2px(context,180);

        }
        Log.e("picadapter", "getView: "+images.get(position));
        Glide.with(context).load(images.get(position)).centerCrop().into(imageView);
//        Glide.with(context).load(R.drawable.action_scan).into(imageView);

        return view;
    }
}
