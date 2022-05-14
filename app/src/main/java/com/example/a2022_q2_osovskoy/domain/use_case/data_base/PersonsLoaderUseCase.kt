package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.toPerson
import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase

interface PersonsLoaderUseCase {

    suspend fun load(): ResultState<List<Person>>

    class Base(
        private val baseUseCase: BaseUseCase,
        private val personsRepository: PersonsRepository,
    ) : PersonsLoaderUseCase {

        override suspend fun load(): ResultState<List<Person>> = baseUseCase.execute {
            personsRepository.loadPersonsDto().map { personDto ->
                personDto.toPerson()
            }
        }
    }
}