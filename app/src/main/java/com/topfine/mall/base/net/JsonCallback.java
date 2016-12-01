package com.topfine.mall.base.net;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Littlezuo on 2016/11/18.
 */

public abstract class JsonCallback<T> extends StringCallback {
    private Class<T> clazz;

    public JsonCallback(Class<T> clazz) {

        this.clazz = clazz;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
    }

    @Override
    public void onSuccess(String s, Call call, Response response) {

//        //解析json
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(s);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        final int code = jsonObject.optInt("code", 0);
//        final String message = jsonObject.optString("message", "");
//        String result = jsonObject.optString("data", "");
//        if (code == 0) {
//            if (clazz != null) {
//                T t = Convert.fromJson(s, clazz);
//                onJsonSuccess(t, call, response);
//            }
//        } else {
//            //如果要更新UI，需要使用handler，可以如下方式实现，也可以自己写handler
//            OkGo.getInstance().getDelivery().post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(OkGo.getContext(), message, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
        T t = Convert.fromJson(s, clazz);
        onJsonSuccess(t, call, response);

    }

    @Override
    public void onError(Call call, Response response, Exception e) {
        super.onError(call, response, e);

    }

    @Override
    public void onCacheSuccess(String s, Call call) {
        super.onCacheSuccess(s, call);
    }


    public abstract void onJsonSuccess(T t, Call call, Response response);


}
