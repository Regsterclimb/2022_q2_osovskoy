package com.example.a2022_q2_osovskoy.domain.use_case.file

import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.toPerson
import com.example.a2022_q2_osovskoy.domain.repository.PersonsFileRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase

interface FilePersonsLoaderUseCase {

    suspend fun loadPersons(): ResultState<List<Person>>

    class Base(
        private val personsFileRepository: PersonsFileRepository,
        private val baseUseCase: BaseUseCase,
    ) : FilePersonsLoaderUseCase {

        override suspend fun loadPersons() = baseUseCase.execute {
            personsFileRepository.loadPersonsDto().map { personDto ->
                personDto.toPerson()
            }
        }
    }
}