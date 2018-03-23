///**
// * Copyright 2017 Harish Sridharan
// * <p>
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// * <p>
// * http://www.apache.org/licenses/LICENSE-2.0
// * <p>
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.example.wangguilong.microweibo.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import com.example.wangguilong.microweibo.R;
//import com.example.wangguilong.microweibo.bean.HomeBean;
//import com.example.wangguilong.microweibo.bean.TestBean;
//import com.example.wangguilong.microweibo.callback.OnItemClickListener;
//import com.example.wangguilong.microweibo.view.circularimage.CircularImageView;
//import com.example.wangguilong.microweibo.view.likebutton.LikeButton;
//import com.example.wangguilong.microweibo.view.likebutton.OnLikeListener;
//import com.example.wangguilong.microweibo.widget.WeiBoContentTextUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ItemHolder> implements View.OnClickListener {
//
////    private List<ItemCard> mCards = new ArrayList<>();
//    private Context context;
////    private List<HomeBean> list;
//    private TestBean bean;
//    private OnItemClickListener itemClickListener;
//
//    public CardAdapter(Context context,TestBean bean) {
//        this.context = context;
//        this.bean = bean;
//    }
//
//    @Override
//    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.layout_card_item,parent,false);
//        return new ItemHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(ItemHolder holder, int position) {
//        ItemHolder itemHolder = holder;
//        if (itemHolder!=null) {
////            itemHolder.text1.setText(list.get(position).getStatuses().get(position).getUser().getName());
////            list.get(position).getText());
//            itemHolder.text1.setText(WeiBoContentTextUtil.getWeiBoContent(list.get(position).getText(),context,itemHolder.text1));
//
//            itemHolder.userName.setText(list.get(position).getUserName());
//            itemHolder.headCover.setOnClickListener(this);
//            itemHolder.itemView.setOnClickListener(this);
//            itemHolder.repost.setOnClickListener(this);
//            itemHolder.favorite.setOnLikeListener(new OnLikeListener() {
//                @Override
//                public void liked(LikeButton likeButton) {
//                    likeButton.setLiked(true);
//                }
//
//                @Override
//                public void unLiked(LikeButton likeButton) {
//                    likeButton.setLiked(false);
//                }
//            });
//
//            itemHolder.like.setOnLikeListener(new OnLikeListener() {
//                @Override
//                public void liked(LikeButton likeButton) {
//                    likeButton.setLiked(true);
//                }
//
//                @Override
//                public void unLiked(LikeButton likeButton) {
//                    likeButton.setLiked(false);
//                }
//            });
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    @Override
//    public void onClick(View view) {
//        itemClickListener.onItemClick(view);
//    }
//
////    public void setCards(List<ItemCard> cards) {
////        if (cards == null) {
////            return;
////        }
////
////        mCards = cards;
////    }
////
////    public void setType(int type) {
////        this.mType = type;
////    }
//
//    public static class ItemHolder extends RecyclerView.ViewHolder {
//        private TextView text1,userName;
//        private LikeButton favorite,like;
//        private CircularImageView headCover;
//        private ImageView repost;
//        public ItemHolder(View itemView) {
//            super(itemView);
//            text1 = itemView.findViewById(R.id.text);
//            userName = itemView.findViewById(R.id.user_name);
//            favorite = itemView.findViewById(R.id.favorite);
//            like = itemView.findViewById(R.id.like);
//            headCover = itemView.findViewById(R.id.profile_image);
//            repost = itemView.findViewById(R.id.repost_image);
//
//        }
//    }
//
//    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }
//
//}
