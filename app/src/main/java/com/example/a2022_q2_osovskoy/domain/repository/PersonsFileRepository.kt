package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.Person

interface PersonsFileRepository {

    suspend fun uploadFirst()

    suspend fun loadPersons(): List<Person>

    suspend fun deleteAllPersons()
}