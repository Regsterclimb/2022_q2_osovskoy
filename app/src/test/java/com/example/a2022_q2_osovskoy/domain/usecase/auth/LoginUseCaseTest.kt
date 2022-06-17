package com.example.a2022_q2_osovskoy.domain.usecase.auth

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.times
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify


@ExperimentalCoroutinesApi
class LoginUseCaseTest {

    lateinit var authRepository: AuthRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        authRepository = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() = runTest {

        val loginUseCase = LoginUseCase(authRepository)

        loginUseCase(BaseUser("Олег", "1234"))

        verify(authRepository, times(1))
            .login(BaseUser("Олег", "1234"))

    }
}