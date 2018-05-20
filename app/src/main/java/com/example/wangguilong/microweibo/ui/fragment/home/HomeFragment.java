package com.example.wangguilong.microweibo.ui.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.wangguilong.microweibo.R;
import com.example.wangguilong.microweibo.adapter.BaseAdapter;
import com.example.wangguilong.microweibo.adapter.HomeAdapter;
import com.example.wangguilong.microweibo.bean.TestBean;
import com.example.wangguilong.microweibo.ui.activity.weibo.WeiboDetailActivity;
import com.example.wangguilong.microweibo.ui.fragment.base.BaseFragment;
import com.example.wangguilong.microweibo.util.Util;
import com.example.wangguilong.microweibo.view.shimmer.ShimmerRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangGuiLong on 2018/3/10.
 */

public class HomeFragment extends BaseFragment implements HomeContract.IHomeView, BaseAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnItemLongClickListener {

    private ShimmerRecyclerView shimmerRecyclerView;
    private HomePresenter homePresenter;
    private HomeAdapter adapter;
    private List<TestBean.StatusesBean> beans = new ArrayList<>();
    private int page = 1;
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected int setLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        //初始化P层
        homePresenter = new HomePresenter(this);
        homePresenter.getData(page);
        //设置适配器
        setAdapter();
        //设置监听
        setListener();


    }

    private void getLimit() {
        OkGo.<String>get("https://api.weibo.com/2/account/rate_limit_status.json")
                .params("access_token",Util.getAccessToken(getContext()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("HomeF", "limit: "+response.body() );
                    }

                });
    }

    private void setListener() {
        adapter.setOnItemClickListener(this);
        adapter.setOnItemLongClickListener(this);
        refreshLayout.setOnRefreshListener(this);
        shimmerRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //可见item
                int visibleItemCount = manager.getChildCount();
                //全部item
                int totalItemCount = manager.getItemCount();
                if (!recyclerView.canScrollVertically(1)) {
                    //如果全部item大于可见item
                    if (totalItemCount > visibleItemCount && !adapter.getLoading()) {
                        adapter.setLoading(true);
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        page++;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                homePresenter.getData(page);
                adapter.setLoading(false);
            }
        }, 1000);

    }

    private void setAdapter() {

        adapter = new HomeAdapter(getcontext(), shimmerRecyclerView, beans, R.layout.layout_card_item);
        shimmerRecyclerView.setLayoutManager(new LinearLayoutManager(getcontext()));

        shimmerRecyclerView.setAdapter(adapter);
        shimmerRecyclerView.showShimmerAdapter();
        shimmerRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                shimmerRecyclerView.hideShimmerAdapter();
            }
        }, 2000);
    }

    @Override
    protected void initData() {
        getLimit();
        findView();
        Log.e("home", "getuid: " + Util.getUID(getMContext()));
        //设置refreshlayout的属性
        initRefresh();
    }

    /**
     * 设置refreshlayout的属性
     */
    private void initRefresh() {
        refreshLayout.setColorSchemeResources(R.color.yellow, R.color.red, R.color.blue);
    }

    private void findView() {
        shimmerRecyclerView = findViewById(R.id.shimmer_recycler_view);
        refreshLayout = findViewById(R.id.refresh_layout);
    }

    @Override
    public Context getcontext() {
        return getMContext();
    }

    @Override
    public void showMsg(String msg) {
        Util.t(getcontext(), msg);
    }

    @Override
    public void getDataSuccess(List<TestBean.StatusesBean> list) {
        if (list != null && list.size() > 0) {
            if (page == 1) {
                beans.clear();
            }
            beans.addAll(list);
        } else {
//            if (page == 1 && list.size() == 0) {
                Util.t(getcontext(), "无数据");
//            }
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(View view, int position) {
//        switch (view.getId()) {
//            case R.id.profile_image:
//                Util.t(getcontext(), "点击了头像");
//                break;
//            case R.id.sss:
        Intent intent = new Intent();
        intent.putExtra("beans", beans.get(position));
        intent.setClass(getcontext(), WeiboDetailActivity.class);
        startActivity(intent);
//                break;
//            case R.id.repost_image:
//                Util.t(getcontext(), "点击了转发");
//                break;
//        }
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(true);
        page = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                homePresenter.getData(page);
                //不能放在外面 不然会直接消失
                refreshLayout.setRefreshing(false);
            }
        }, 2000);


    }

    @Override
    public void onItemLongClick(View view, int position) {
        Util.t(getContext(), "长按" + position);
    }
}
