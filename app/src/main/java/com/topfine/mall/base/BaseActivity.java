package com.topfine.mall.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;

import com.topfine.mall.base.event.ConnectionChangeReceiver;

/**
 * Created by Littlezuo on 2016/11/17.
 */

public class BaseActivity extends FragmentActivity {

    private ConnectionChangeReceiver mConnectionChangeReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        EventBus.getDefault().register(this);
    }



    //启动一个新的activity
    public void goToActivity(Class activity,Bundle bundle){
        Intent intent = new Intent(this,activity);
        if(bundle != null){
            intent.putExtra("data",bundle);
        }
        startActivity(intent);
    }

    //结束当前activity的显示
    public void closeCurrentActivity(){
        AppManager.getInstance().removeCurrent();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
//        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mConnectionChangeReceiver);
        super.onDestroy();
    }
}
