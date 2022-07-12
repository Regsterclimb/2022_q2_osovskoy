package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.LocalLoansRepository
import javax.inject.Inject

class DeleteLoansUseCase @Inject constructor(private val localLoansRepository: LocalLoansRepository) {

    suspend operator fun invoke() {
        localLoansRepository.deleteAll()
    }
}