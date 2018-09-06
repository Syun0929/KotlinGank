package com.zjy.kotlingank.api

import com.zjy.kotlingank.base.BaseEntity
import com.zjy.kotlingank.bean.Meizi
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GankApi {
    @GET("data/福利/10/{page}")
    abstract fun getMeizi(@Path("page") page: Int): Observable<BaseEntity<List<Meizi>>>
}