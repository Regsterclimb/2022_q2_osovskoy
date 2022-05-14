package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository

interface PersonCreateUseCase {

    suspend fun create(person: Person)

    class Base(private val personOperationsRepository: PersonOperationsRepository) :
        PersonCreateUseCase {

        override suspend fun create(person: Person) = personOperationsRepository.create(person)
    }
}