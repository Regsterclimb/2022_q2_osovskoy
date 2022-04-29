package com.example.a2022_q2_osovskoy.domain.use_case

sealed class ResultState<T>(val data: T?) {
    class Success<T>(data: T) : ResultState<T>(data)
    class Error<T> : ResultState<T>(data = null)
}