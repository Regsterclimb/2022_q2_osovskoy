package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class UpdateAppConfigUseCaseTest {

    private lateinit var appConfigRep: AppConfigRepository

    @Before
    fun setUp() {
        appConfigRep = mock()
    }

    @Test
    fun `WHEN invoke Expect correct Result`() {
        val appConfig = AppConfig.BASE

        val loginUseCase = UpdateAppConfigUseCase(appConfigRep)

        loginUseCase(appConfig)

        verify(appConfigRep, Mockito.times(1)).update(AppConfig.BASE)
    }
}