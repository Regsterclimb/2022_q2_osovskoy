package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import kotlinx.coroutines.CoroutineDispatcher

interface BaseRepository {

    suspend fun <T> execute(dispatcher: CoroutineDispatcher, block: suspend () -> T): ResultState<T>
}