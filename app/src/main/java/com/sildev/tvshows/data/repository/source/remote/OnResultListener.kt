package com.sildev.tvshows.data.repository.source.remote

interface OnResultListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception?)
}
