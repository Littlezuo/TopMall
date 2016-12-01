package com.topfine.mall.base;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;

import com.lzy.okgo.OkGo;
import com.topfine.mall.R;
import com.topfine.mall.base.multiplepage.LoadingAndRetryManager;
import com.topfine.mall.base.net.OKHttpFactory;
import com.topfine.mall.base.event.ConnectionChangeReceiver;

/**
 * Created by Littlezuo on 2016/9/27.
 */
public class MyApplication extends Application{
    private static Context mContext;
    public static Handler handler;
    public static Thread mainThread;//主线程
    public static int mainThreadId;
    ConnectionChangeReceiver mNetworkStateReceiver;
    private ConnectionChangeReceiver mConnectionChangeReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);
        mContext = MyApplication.this;
        handler = new Handler();
        mainThread = Thread.currentThread();//获取实例化Application的线程,就是主线程
        mainThreadId = android.os.Process.myTid();
        OKHttpFactory.init(this);
        //注册网络状态监听广播
        initNetStateBrocast();
        //初始化各页面层
        initMultipePage();
    }

    private void initNetStateBrocast() {
        mConnectionChangeReceiver = new ConnectionChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mConnectionChangeReceiver,filter);
    }

    private void initMultipePage() {
        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.view_pager_neterror;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.view_pager_loading;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.view_page_empty;
    }


    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onTerminate() {
        unregisterReceiver(mConnectionChangeReceiver);
        super.onTerminate();
    }
}
