package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.model.Person

interface PersonDataBaseRepository {

    fun create(person: Person)
    fun remove(person: Person)
    fun update(person: Person)
}