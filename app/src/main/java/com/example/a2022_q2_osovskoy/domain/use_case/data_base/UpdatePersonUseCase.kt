package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository

class UpdatePersonUseCase(private val personOperationsRepository: PersonOperationsRepository) {

    suspend operator fun invoke(person: Person) {
        update(person)
    }

    private suspend fun update(person: Person) = personOperationsRepository.update(person)
}
