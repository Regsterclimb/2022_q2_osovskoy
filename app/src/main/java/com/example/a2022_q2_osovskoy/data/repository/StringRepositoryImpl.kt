package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasource.StringLocalDataSource
import com.example.a2022_q2_osovskoy.domain.repository.StringRepository
import javax.inject.Inject


class StringRepositoryImpl @Inject constructor(
    private val localDataSource: StringLocalDataSource,
    private val remoteLocalDataSource: StringLocalDataSource,
) : StringRepository {

    override fun getFromRemote(): String = localDataSource.get()

    override fun getFromLocal(): String = remoteLocalDataSource.get()
}