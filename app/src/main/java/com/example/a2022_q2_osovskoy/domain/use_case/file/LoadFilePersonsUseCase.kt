package com.example.a2022_q2_osovskoy.domain.use_case.file

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.PersonsFileRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase


class LoadFilePersonsUseCase(
    private val personsFileRepository: PersonsFileRepository,
    private val baseUseCase: BaseUseCase,
) {

    suspend operator fun invoke(): ResultState<List<Person>> = loadPersons()

    private suspend fun loadPersons(): ResultState<List<Person>> = baseUseCase.execute {
        personsFileRepository.loadPersons()
    }
}