package com.example.a2022_q2_osovskoy.data.datasourse

import com.example.a2022_q2_osovskoy.domain.entities.Dog
import javax.inject.Inject

class DogsDataSourceImpl @Inject constructor() : AnimalDataSource {

    override suspend fun get(): List<Dog> = listOf(Dog("Michel", "4"))
}