package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase

class RemoveAllPersonsUseCase(
    private val baseUseCase: BaseUseCase,
    private val personsRepository: PersonsRepository,
) {
    suspend operator fun invoke() {
        deleteAll()
    }

    private suspend fun deleteAll() = baseUseCase.execute {
        personsRepository.deleteAllPersonDto()
    }
}