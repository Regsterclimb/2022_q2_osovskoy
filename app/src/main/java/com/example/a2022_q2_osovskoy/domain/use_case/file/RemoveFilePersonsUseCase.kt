package com.example.a2022_q2_osovskoy.domain.use_case.file

import com.example.a2022_q2_osovskoy.domain.repository.PersonsFileRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase


class RemoveFilePersonsUseCase(
    private val personsFileRepository: PersonsFileRepository,
    private val baseUseCase: BaseUseCase,
) {
    suspend operator fun invoke() = deleteAll()

    private suspend fun deleteAll() = baseUseCase.execute {
        personsFileRepository.deleteAllPersons()
    }
}