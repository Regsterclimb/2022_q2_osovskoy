package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetAppConfigUseCaseTest {

    lateinit var appConfigRep: AppConfigRepository

    @Before
    fun setUp() {
        appConfigRep = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() {
        val appConfig = AppConfig.BASE

        whenever(appConfigRep.get()).thenReturn(appConfig)
        val useCase = GetAppConfigUseCase(appConfigRep)

        val expected = AppConfig.BASE

        val actual = useCase()

        assertEquals(expected, actual)
    }
}