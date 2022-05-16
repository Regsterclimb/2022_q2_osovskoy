package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository

class RemovePersonUseCase(private val personOperationsRepository: PersonOperationsRepository) {

    suspend operator fun invoke(person: Person) {
        remove(person)
    }

    private suspend fun remove(person: Person) = personOperationsRepository.remove(person)
}