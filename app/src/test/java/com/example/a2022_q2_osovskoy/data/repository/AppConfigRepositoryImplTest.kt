package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.local.AppConfigDataSource
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AppConfigRepositoryImplTest {

    private lateinit var appConfigDataSource: AppConfigDataSource

    @Before
    fun setUp() {
        appConfigDataSource = mock()
    }

    @Test
    fun `WHEN get Expect correct Result`() {
        val appConfig = AppConfig.BASE

        whenever(appConfigDataSource.get()).thenReturn(appConfig)

        val rep = AppConfigRepositoryImpl(appConfigDataSource)

        val expected = AppConfig.BASE

        val actual = rep.get()

        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN update Expect correct Result`() {
        val appConfig = AppConfig.BASE

        val rep = AppConfigRepositoryImpl(appConfigDataSource)

        val expected = AppConfig.BASE

        rep.update(appConfig)

        verify(appConfigDataSource, times(1)).update(expected)
    }

}