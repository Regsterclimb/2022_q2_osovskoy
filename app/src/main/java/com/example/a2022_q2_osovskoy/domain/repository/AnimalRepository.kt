package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entities.MyAnimal

interface AnimalRepository {

    suspend fun getAnimals(): List<MyAnimal>
}