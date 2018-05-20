package com.example.wangguilong.microweibo.ui.activity.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.ui.activity.login.LoginActivity;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout cache,check,about;
    private TextView loginOut;
    private ImageView back;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initData() {
        super.initData();
        findView();
    }

    private void findView() {
         cache = findViewById(R.id.cache_relat);
         check = findViewById(R.id.check_relat);
         about = findViewById(R.id.about_relat);
         loginOut =findViewById(R.id.login_out_text);
         back = findViewById(R.id.back_image);
    }

    @Override
    protected void setListener() {
        super.setListener();
        loginOut.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_out_text:
                SharedPreferences sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove("access_token");
                edit.commit();
                Intent intent = new Intent();
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.back_image:
                finish();
                break;
        }
    }
}
