package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoginDataSource
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.LoginState
import com.example.a2022_q2_osovskoy.domain.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : LoginRepository {

    //todo() dry
    override suspend fun logIn(baseUser: BaseUser): LoginState = withContext(dispatcher) {
        try {
            loginDataSource.login(baseUser)
            LoginState.Success
        } catch (e: RuntimeException) {
            LoginState.Error
        }
    }

    override suspend fun register(baseUser: BaseUser): LoginState = withContext(dispatcher) {
        try {
            loginDataSource.register(baseUser)
            LoginState.Success
        } catch (e: RuntimeException) {
            e.printStackTrace()
            LoginState.Error
        }
    }
}