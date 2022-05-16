package com.example.a2022_q2_osovskoy.domain.use_case.data_base

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase


class UploadPersonsFirstTimeUseCase(
    private val baseUseCase: BaseUseCase,
    private val personsRepository: PersonsRepository,
) {
    //todo() во всех юзкейсах сделать ResultState
    suspend operator fun invoke(): ResultState<Unit> = upload()


    private suspend fun upload(): ResultState<Unit> = baseUseCase.execute {
        personsRepository.uploadFirst()
    }
}