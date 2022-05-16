package com.example.a2022_q2_osovskoy.data.repository

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface BaseRepository {

    suspend fun <T> execute(block: suspend () -> T): T

    class Base(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : BaseRepository {

        override suspend fun <T> execute(block: suspend () -> T): T = withContext(dispatcher) {
            try {
                block.invoke()
            } catch (e: CancellationException) {
                throw e
            }
            catch (e: RuntimeException) {
                throw e
            }
        }
    }
}