package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository

interface PersonRemoveUseCase {

    suspend fun remove(person: Person)

    class Base(private val personOperationsRepository: PersonOperationsRepository) :
        PersonRemoveUseCase {

        override suspend fun remove(person: Person) = personOperationsRepository.remove(person)
    }
}