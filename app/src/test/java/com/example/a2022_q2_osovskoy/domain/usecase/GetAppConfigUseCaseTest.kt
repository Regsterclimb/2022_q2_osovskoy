package com.example.a2022_q2_osovskoy.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetAppConfigUseCaseTest {

    lateinit var appConfigRep: AppConfigRepository

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

        whenever(appConfigRep.get()).thenReturn(appConfig)
        val useCase = GetAppConfigUseCase(appConfigRep)

        val expected = AppConfig.BASE

        val actual = useCase()

        assertEquals(expected, actual)
    }
}