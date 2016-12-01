package com.topfine.mall.base.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.topfine.mall.R;
import com.topfine.mall.utils.NetUtil;
import com.topfine.mall.utils.ToastUtils;


/**
 * Created by Littlezuo on 2016/11/22.
 * 网络状态改变的广播
 */

public class ConnectionChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isNet = NetUtil.isConnected(context);

        if(!isNet) {
            ToastUtils.showShortToast(context,context.getString(R.string.your_network_has_disconnected));
        }
        boolean isMobile = NetUtil.isMobile(context);
        if(isMobile) {
            ToastUtils.showShortToast(context,context.getString(R.string.your_mobile_has_disconnected));
        }

    }
}
