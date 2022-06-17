package com.example.a2022_q2_osovskoy.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
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
class UpdateAppConfigUseCaseTest {

    private lateinit var appConfigRep: AppConfigRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        appConfigRep = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() = runTest {
        val appConfig = AppConfig.BASE

        val loginUseCase = UpdateAppConfigUseCase(appConfigRep)

        loginUseCase(appConfig)

        verify(appConfigRep, Mockito.times(1)).update(AppConfig.BASE)
    }
}