package com.example.a2022_q2_osovskoy.data.datasourse

import com.example.a2022_q2_osovskoy.domain.entities.Rat
import javax.inject.Inject

class RatsDataSourceImpl @Inject constructor() : AnimalDataSource {

    override suspend fun get(): List<Rat> = listOf(Rat("Christie", "1"),Rat("Victoria", "4"))

}
