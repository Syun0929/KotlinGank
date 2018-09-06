package com.zjy.kotlingank.base

import android.accounts.NetworkErrorException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

abstract class BaseObserver<T> : Observer<BaseEntity<T>> {


    constructor() {

    }

    override fun onSubscribe(d: Disposable) {
        onRequestStart()
    }

    override fun onNext(tBaseEntity: BaseEntity<T>) {
        onRequestEnd()
        if (tBaseEntity.isSuccess()) {
            try {
                this.onSuccess(tBaseEntity.results)
            } catch (e: Exception) {
                e.printStackTrace()
                onFailure(e, false)
            }

        } else {
            onCodeError(tBaseEntity);
        }
    }

    override fun onError(e: Throwable) {
        onRequestEnd()
        try {
            if (e is ConnectException
                    || e is TimeoutException
                    || e is NetworkErrorException
                    || e is UnknownHostException
                    || e is SocketTimeoutException) {
                onFailure(e, true)
            } else {
                onFailure(e, false)
            }
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

    }

    override fun onComplete() {

    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    @Throws(Exception::class)
    protected abstract fun onSuccess(t: T?)

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    @Throws(Exception::class)
    protected fun onCodeError(t: BaseEntity<T>) {
    }

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    @Throws(Exception::class)
    protected abstract fun onFailure(e: Throwable, isNetWorkError: Boolean)

    protected fun onRequestStart() {}

    protected fun onRequestEnd() {}


}