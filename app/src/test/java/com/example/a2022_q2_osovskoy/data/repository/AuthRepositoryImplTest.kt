package com.example.a2022_q2_osovskoy.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.data.datasourse.local.TokenDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthDataSource
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AuthRepositoryImplTest {

    private lateinit var authDataSource: AuthDataSource
    private lateinit var dispatcher: CoroutineDispatcher
    private lateinit var tokenDataSource: TokenDataSource

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        authDataSource = mock()
        dispatcher = Dispatchers.Unconfined
        tokenDataSource = mock()
    }

    @Test
    fun `WHEN register Expect correct Result`() = runTest {
        val baseUser = BaseUser("Олег","1234")

        val authRep = AuthRepositoryImpl(authDataSource, dispatcher, tokenDataSource)

        authRep.register(baseUser)

        verify(authDataSource, Mockito.times(1))
            .register(BaseUser("Олег", "1234"))

    }

    @Test
    fun `WHEN login Expect correct Result`() = runTest {
        val baseUser = BaseUser("Олег","1234")
        val string = "123455"
        val responseBody = string.toResponseBody()

        whenever(authDataSource.login(baseUser)).thenReturn(responseBody)
        val authRep = AuthRepositoryImpl(authDataSource, dispatcher, tokenDataSource)

        authRep.login(baseUser)

        verify(tokenDataSource, Mockito.times(1)).update("123455")
    }
}