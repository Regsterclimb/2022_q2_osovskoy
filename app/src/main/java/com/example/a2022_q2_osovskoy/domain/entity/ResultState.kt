package com.example.a2022_q2_osovskoy.domain.entity


sealed class ResultState<T> {
    class Success<T>(val result: T) : ResultState<T>()
    class Error<T>() : ResultState<T>()
}
