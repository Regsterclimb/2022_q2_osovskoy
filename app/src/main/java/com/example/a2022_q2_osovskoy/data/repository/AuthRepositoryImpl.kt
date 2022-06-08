package com.example.a2022_q2_osovskoy.data.repository

import android.util.Log
import com.example.a2022_q2_osovskoy.data.datasourse.local.authconfig.AuthConfigDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthDataSource
import com.example.a2022_q2_osovskoy.domain.entity.AuthResult
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val dispatcher: CoroutineDispatcher,
    private val authConfigDataSource: AuthConfigDataSource,
) : AuthRepository {

    //todo() dry
    override suspend fun login(baseUser: BaseUser): AuthResult = withContext(dispatcher) {
        try {
            val result = authDataSource.login(baseUser)
            Log.d("ResponseBody", result)
            AuthResult.Login.Success
        } catch (e: RuntimeException) {
            e.printStackTrace()
            AuthResult.Login.Error
        }
    }

    override suspend fun register(baseUser: BaseUser): AuthResult = withContext(dispatcher) {
        try {
            authDataSource.register(baseUser)
            AuthResult.Registration.Success
        } catch (e: RuntimeException) {
            e.printStackTrace()
            AuthResult.Registration.Error
        }
    }

    override suspend fun getAuthConfig(): LoginConfigState = withContext(dispatcher) {
        authConfigDataSource.get()
    }

    override suspend fun updateAuthConfig(loginConfigState: LoginConfigState) {
        withContext(dispatcher) {
            authConfigDataSource.update(loginConfigState)
        }
    }
}