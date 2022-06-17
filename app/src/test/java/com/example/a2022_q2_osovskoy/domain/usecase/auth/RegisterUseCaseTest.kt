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
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class RegisterUseCaseTest {

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

        val regUseCase = RegisterUseCase(authRepository)

        regUseCase(BaseUser("Олег", "1234"))

        verify(authRepository, Mockito.times(1))
            .register(BaseUser("Олег", "1234"))

    }
}