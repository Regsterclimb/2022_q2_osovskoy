package com.example.a2022_q2_osovskoy.domain.use_case.file

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.PersonsFileRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase

class UploadFileFirstTimeUseCase(
    private val personsFileRepository: PersonsFileRepository,
    private val baseUseCase: BaseUseCase,
) {
    suspend operator fun invoke(): ResultState<Unit> = uploadFirst()

    private suspend fun uploadFirst() = baseUseCase.execute {
        personsFileRepository.uploadFirst()
    }
}