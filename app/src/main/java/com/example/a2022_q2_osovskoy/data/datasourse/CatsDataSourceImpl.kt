package com.example.a2022_q2_osovskoy.data.datasourse

import com.example.a2022_q2_osovskoy.domain.entities.Cat
import javax.inject.Inject

class CatsDataSourceImpl @Inject constructor() : AnimalDataSource {

    override suspend fun get(): List<Cat> = listOf(Cat("John", "10"))

}