package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase

interface PersonsRemoverUseCase {

    suspend fun deleteAll(): ResultState<Unit>

    class Base(
        private val baseUseCase: BaseUseCase,
        private val personsRepository: PersonsRepository,
    ) : PersonsRemoverUseCase {

        override suspend fun deleteAll() = baseUseCase.execute {
            personsRepository.deleteAllPersonDto()
        }
    }
}