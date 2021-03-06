package com.example.a2022_q2_osovskoy.data.datasourse.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.data.datasourse.network.AuthApi
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AuthRemoteDataSourceImplTest {

    private lateinit var authApi: AuthApi

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        authApi = mock()
    }


    @Suppress("BlockingMethodInNonBlockingContext")
    @Test
    fun `WHEN login Expect correct Result`() = runTest {
        val name = "Олег"
        val password = "1234"
        val responseString = "12355127328745"

        val responseBody: ResponseBody = responseString.toResponseBody()

        whenever(authApi.login(BaseUser(name, password))).thenReturn(responseBody)

        val expected = "12355127328745"

        val actual = authApi.login(BaseUser("Олег", "1234")).string()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN register Expect correct Result`() = runTest {
        val baseUser = BaseUser("Олег", "1234")

        val remoteRep = AuthRemoteDataSourceImpl(authApi)

        remoteRep.register(baseUser)

        verify(authApi, Mockito.times(1))
            .register(BaseUser("Олег", "1234"))
    }
}