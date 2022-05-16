package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.domain.entity.ResultState

interface BaseUseCase {

    suspend fun <T> execute(block: suspend () -> T): ResultState<T>

    class Base : BaseUseCase {

        override suspend fun <T> execute(block: suspend () -> T): ResultState<T> = try {
            val result = block.invoke()
            ResultState.Success(result = result)
        } catch (e: Exception) {
            //todo()
            ResultState.Error()
        }
    }
}