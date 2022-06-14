package com.example.a2022_q2_osovskoy.domain.entity

sealed class ResultState<T>(val data: T? = null,val error : Throwable? = null) {
    class Success<T>(data : T) : ResultState<T>(data)
    class Error<T>(error: Throwable?,data: T?=null) : ResultState<T>(data,error)
}
