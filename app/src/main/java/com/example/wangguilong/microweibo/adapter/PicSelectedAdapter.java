package com.example.wangguilong.microweibo.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wangguilong.microweibo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 77622 on 2018/5/18.
 */

public class PicSelectedAdapter extends BaseAdapter {
    private Context context;
    private List<Uri> images = new ArrayList<>();

    public PicSelectedAdapter(Context context, List<Uri> images) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.gridview_item_layout2, parent, false);
        ImageView imageView = view.findViewById(R.id.gridview_item_image);

        Glide.with(context).load(images.get(position)).centerCrop().into(imageView);
//        Glide.with(context).load(R.drawable.action_scan).into(imageView);

        return view;
    }
}
