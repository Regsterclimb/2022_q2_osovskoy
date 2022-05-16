package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase

class LoadPersonsUseCase(
    private val baseUseCase: BaseUseCase,
    private val personsRepository: PersonsRepository,
) {
    suspend operator fun invoke(): ResultState<List<Person>> = load()

    private suspend fun load(): ResultState<List<Person>> = baseUseCase.execute {
        personsRepository.loadPersons()
    }
}