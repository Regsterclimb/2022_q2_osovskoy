package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.Person

interface PersonOperationsRepository {

    suspend fun create(person: Person)

    suspend fun remove(person: Person)

    suspend fun update(person: Person)
}