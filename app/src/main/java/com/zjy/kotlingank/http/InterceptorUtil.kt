package com.zjy.kotlingank.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

object InterceptorUtil {
    var TAG = "InterceptorUtil--->"

    fun LogInterceptor(): HttpLoggingInterceptor? {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d(TAG,"HttpLog: $message")  })
                .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun HeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val mRequest = chain.request()
            //在这里你可以做一些想做的事,比如token失效时,重新获取token
            //或者添加header等等,PS我会在下一篇文章总写拦截token方法
            chain.proceed(mRequest)
        }

    }

}