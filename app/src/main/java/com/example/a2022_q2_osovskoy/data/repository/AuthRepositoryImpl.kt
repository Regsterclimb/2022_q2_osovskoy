package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.TokenDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthDataSource
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import com.example.a2022_q2_osovskoy.extentions.myExecute
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val tokenDataSource: TokenDataSource,
) : AuthRepository {

    override suspend fun login(baseUser: BaseUser): ResultState<Unit> = withContext(dispatcher) {
        try {
            tokenDataSource.update(authDataSource.login(baseUser).charStream().readText())
            ResultState.Success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: RuntimeException) {
            ResultState.Error(e)
        }
    }

    override suspend fun register(baseUser: BaseUser) {
        dispatcher.myExecute {
            authDataSource.register(baseUser)
        }
    }
}