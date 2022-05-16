package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository

class CreatePersonUseCase(private val personOperationsRepository: PersonOperationsRepository) {

    suspend operator fun invoke(person: Person) {
        create(person)
    }

    private suspend fun create(person: Person) = personOperationsRepository.create(person)
}