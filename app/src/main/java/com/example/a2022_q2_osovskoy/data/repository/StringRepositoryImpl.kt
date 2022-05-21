package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasource.StringDataSource
import com.example.a2022_q2_osovskoy.di.annotation.Local
import com.example.a2022_q2_osovskoy.di.annotation.Remote
import com.example.a2022_q2_osovskoy.domain.entity.MockedString
import com.example.a2022_q2_osovskoy.domain.repository.StringRepository
import com.example.a2022_q2_osovskoy.extentions.toMockedString
import javax.inject.Inject


class StringRepositoryImpl @Inject constructor(
    @Local private val localDataSource: StringDataSource,
    @Remote private val remoteDataSource: StringDataSource,
) : StringRepository {

    override fun getFromRemote(): MockedString = localDataSource.get().toMockedString()

    override fun getFromLocal(): MockedString = remoteDataSource.get().toMockedString()
}