package com.zjy.kotlingank.http

import com.zjy.kotlingank.api.GankApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpManager {

    private fun getRetrofit(): Retrofit{
        val mOkHttpClient = OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.HeaderInterceptor())
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                .build()
        return Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//添加gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//添加rxjava转换器
                .client(mOkHttpClient)
                .build()
    }



    fun <T> create(service: Class<T>): T {
        return getRetrofit().create(service)
    }

    val service:GankApi by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
        getRetrofit().create(GankApi::class.java)
    }
}