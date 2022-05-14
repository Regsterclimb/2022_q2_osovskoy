package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository

interface PersonUpdateUseCase {

    suspend fun update(person: Person)

    class Base(private val personOperationsRepository: PersonOperationsRepository) :
        PersonUpdateUseCase {

        override suspend fun update(person: Person) = personOperationsRepository.update(person)
    }
}