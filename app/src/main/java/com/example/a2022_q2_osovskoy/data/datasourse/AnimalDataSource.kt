package com.example.a2022_q2_osovskoy.data.datasourse

import com.example.a2022_q2_osovskoy.domain.entities.Animal

interface AnimalDataSource {

    suspend fun get(): List<Animal>
}