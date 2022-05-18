package com.example.a2022_q2_osovskoy.data.datasource

import javax.inject.Inject

//todo() dto model
class StringRemoteDataSourceImpl @Inject constructor() : StringRemoteDataSource {

    private val mockedAnswer = "String from remote data source"

    override fun get(): String = mockedAnswer
}