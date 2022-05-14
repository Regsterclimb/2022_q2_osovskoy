package com.example.a2022_q2_osovskoy.domain.use_case.file

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.PersonsFileRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase

interface FileFirstUploadUseCase {

    suspend fun uploadFirst(): ResultState<Unit>

    class Base(
        private val personsFileRepository: PersonsFileRepository,
        private val baseUseCase: BaseUseCase,
    ) : FileFirstUploadUseCase {

        override suspend fun uploadFirst() = baseUseCase.execute {
            personsFileRepository.uploadFirst()
        }
    }
}