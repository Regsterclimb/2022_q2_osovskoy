package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.BaseRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor() :
    BaseRepository {

    override suspend fun <T> execute(
        dispatcher: CoroutineDispatcher,
        block: suspend () -> T,
    ): ResultState<T> = withContext(dispatcher) {
            try {
                ResultState.Success(block.invoke())
            } catch (e: CancellationException) {
                throw e
            } catch (e: RuntimeException) {
                ResultState.Error()
            }
        }
}
