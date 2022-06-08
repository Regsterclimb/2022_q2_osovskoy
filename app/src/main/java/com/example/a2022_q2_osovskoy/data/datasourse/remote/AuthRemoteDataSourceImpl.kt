package com.example.a2022_q2_osovskoy.data.datasourse.remote

import com.example.a2022_q2_osovskoy.data.datasourse.local.token.TokenDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.network.AuthApi
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRemoteDataSourceImpl @Inject constructor(
    private val authApi: AuthApi,
    private val dispatcher: CoroutineDispatcher,
    private val tokenDataSource: TokenDataSource,
) : AuthDataSource {
    //todo() Default dispatcher
    override suspend fun login(baseUser: BaseUser): String = withContext(dispatcher) {
        val bearer = authApi.login(baseUser).charStream().readText()
        tokenDataSource.update(bearer)
        bearer
    }

    override suspend fun register(baseUser: BaseUser) = withContext(dispatcher) {
        authApi.register(baseUser)
    }
}