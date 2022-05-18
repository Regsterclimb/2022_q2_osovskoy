package com.example.a2022_q2_osovskoy.data.datasource

import javax.inject.Inject


class StringLocalLocalDataSourceImpl @Inject constructor() : StringLocalDataSource {

    private val mockedCache = "String from local data source"

    override fun get(): String = mockedCache
}