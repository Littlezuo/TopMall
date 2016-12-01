package com.topfine.mall.base.net;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpParams;
import com.topfine.mall.base.MyApplication;
import com.topfine.mall.base.constant.AppConstants;

import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Littlezuo on 2016/11/20.
 */
public class OKHttpFactory {
    public static void init(Application application) {

        // 进行Md5签名
        HttpParams commonParams = new HttpParams();
        Map<String, String> deviceInfoMap = RequestUtil.getSignedParamMap(MyApplication.getContext(), AppConstants.APPMODE.NATIVE);

        //loop a Map
        for (Map.Entry<String, String> entry : deviceInfoMap.entrySet()) {
            commonParams.put(entry.getKey(), entry.getValue());
        }
        //-----------------------------------------------------------------------------------//
        //初始化
        OkGo.init(application);
        try {

            OkGo.getInstance()

                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo", Level.WARNING, true)

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(3)
//                                        .addCommonHeaders(headers)  //设置全局公共头
                    .addCommonParams(commonParams)    //设置全局公共参数
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
