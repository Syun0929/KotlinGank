package com.zjy.kotlingank.base

class BaseEntity<T> {
    var error: Boolean = false
    var results: T? = null

    fun isSuccess(): Boolean {
        return error == false
    }
}