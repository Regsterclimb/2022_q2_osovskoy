package com.example.a2022_q2_osovskoy.data.datasource

import com.example.a2022_q2_osovskoy.data.model.MockedStringDto
import javax.inject.Inject

class StringLocalDataSourceImpl @Inject constructor() : StringDataSource {

    private val mockedCache = MockedStringDto("String from local data source")

    override fun get(): MockedStringDto = mockedCache
}