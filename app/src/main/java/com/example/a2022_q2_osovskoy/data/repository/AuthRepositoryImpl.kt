package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.TokenDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthDataSource
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import com.example.a2022_q2_osovskoy.extentions.execute
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val tokenDataSource: TokenDataSource,
) : AuthRepository {

    override suspend fun login(baseUser: BaseUser) {
        dispatcher.execute {
            tokenDataSource.update(authDataSource.login(baseUser).charStream().readText())
        }
    }

    override suspend fun register(baseUser: BaseUser) {
        dispatcher.execute { authDataSource.register(baseUser) }
    }
}