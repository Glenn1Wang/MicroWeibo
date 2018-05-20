package com.example.wangguilong.microweibo.ui.activity.send;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.PicSelectedAdapter;
import com.example.wangguilong.microweibo.ui.activity.base.BaseActivity;
import com.example.wangguilong.microweibo.util.HttpUtil;
import com.example.wangguilong.microweibo.util.Util;
import com.example.wangguilong.microweibo.view.gridview.MyGridView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

public class SendActivity extends BaseActivity implements View.OnClickListener {

    private EditText editText;
    private ImageView back,select,send;
    private TextView title;
    private MyGridView gridView;
    private PicSelectedAdapter adapter;
    private static final int REQUEST_CODE_CHOOSE = 7890;
    List<Uri> mSelected = new ArrayList<>();
    List<String> permissionList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_send;
    }

    @Override
    protected void initData() {
        super.initData();
        findView();
    }

    private void postData() {
        //http://sns.whalecloud.com/
        byte[] b = new byte[0];

        if (mSelected.size() == 0) {

            OkGo.<String>post("https://api.weibo.com/2/statuses/share.json")
//                .isMultipart(true)
                    .params("access_token", Util.getAccessToken(this))
//				.params("count",1)
                    .params("status", HttpUtil.toURLEncoded(editText.getText().toString())+" http://sns.whalecloud.com ")
//                .params("pic",)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Log.e("sendmessage", "onSuccess: "+response.message()+response.body());
                            Util.t(SendActivity.this,response.message()+response.body());
                            finish();
                        }
                    });
        } else {
            b = Util.getImageFromLocalByUrl(mSelected.get(0).getPath());
            OkGo.<String>post("https://api.weibo.com/2/statuses/share.json")
                .isMultipart(true)
                    .params("access_token", Util.getAccessToken(this))
//				.params("count",1)
                    .params("status", HttpUtil.toURLEncoded(editText.getText().toString())+" http://sns.whalecloud.com ")
                .params("pic",String.valueOf(b))
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Log.e("sendmessage", "onSuccess: "+response.message()+response.body());
                            Util.t(SendActivity.this,response.message()+response.body());
                            finish();
                        }
                    });
        }


    }

    @Override
    protected void initView() {
        super.initView();
//        //设置pic适配器
//        setAdapter();
    }

//    /**
//     * 设置pic适配器
//     */
//    private void setAdapter() {
//
//
//    }

    private void findView() {
        editText = findViewById(R.id.text_edit);
        back = findViewById(R.id.back_image);
        title = findViewById(R.id.title);
        select = findViewById(R.id.select_pic_image);
        gridView = findViewById(R.id.gridview);
        send = findViewById(R.id.send_image);
    }

    @Override
    protected void setListener() {
        super.setListener();
        back.setOnClickListener(this);
        select.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_image:
                finish();
                break;
            case R.id.select_pic_image:
                //获取权限
                getPermission();
                break;
            case R.id.send_image:
                postData();
                break;

        }
    }

    /**
     * 获取权限
     */
    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);


        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_EXTERNAL_STORAGE);


        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.CAMERA);
        }

        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        } else {
            //选择图片
            selectPic();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                           Util.t(this,"必须同意所有权限才能使用本程序");

                            return;
                        }
                    }
                    //选择图片
                    selectPic();
                } else {
                    Util.t(this,"发生未知错误");

                }
                break;
                default:
        }
    }

    /**
     * 选择图片
     */
    private void selectPic() {
        Matisse.from(SendActivity.this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(9)
//                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(REQUEST_CODE_CHOOSE);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            adapter = new PicSelectedAdapter(this,mSelected);
            gridView.setAdapter(adapter);
//            Log.d("Matisse", "mSelected: " + mSelected);
//            if (mSelected.size()>0) {
//                Glide.with(this).load(mSelected.get(0)).centerCrop().into(test);
//            }
        }
    }

}

