package com.example.a2022_q2_osovskoy.data.datasource

import com.example.a2022_q2_osovskoy.data.model.MockedStringDto
import javax.inject.Inject

class StringRemoteDataSourceImpl @Inject constructor() : StringDataSource {

    private val mockedAnswer = MockedStringDto("String from remote data source")

    override fun get(): MockedStringDto = mockedAnswer
}