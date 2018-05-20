package com.example.wangguilong.microweibo.ui.fragment.message;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.ui.activity.message.MessageActivity;
import com.example.wangguilong.microweibo.ui.fragment.base.BaseFragment;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class MessageFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout atWeibo, atComment, reComment, bmComment;
    private Intent intent;

    @Override
    protected int setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init() {
        intent = new Intent();
        intent.setClass(getMContext(), MessageActivity.class);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        findView();
        setListener();
    }

    private void setListener() {
        atComment.setOnClickListener(this);
        atWeibo.setOnClickListener(this);
        reComment.setOnClickListener(this);
        bmComment.setOnClickListener(this);
    }

    private void findView() {
        atWeibo = findViewById(R.id.at_weibo_Linear);
        atComment = findViewById(R.id.at_comment_linear);
        reComment = findViewById(R.id.re_comment_linear);
        bmComment = findViewById(R.id.bm_comment_linear);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.at_weibo_Linear:  //艾特我的微博


                intent.putExtra("page", 0);
                startActivity(intent);
                break;
            case R.id.at_comment_linear:  //艾特我的评论
                intent.putExtra("page", 1);
                startActivity(intent);
                break;
            case R.id.re_comment_linear:  //回复我的评论
                intent.putExtra("page", 2);
                startActivity(intent);
                break;
            case R.id.bm_comment_linear:  //我的评论
                intent.putExtra("page", 3);
                startActivity(intent);
                break;
        }
    }
}
