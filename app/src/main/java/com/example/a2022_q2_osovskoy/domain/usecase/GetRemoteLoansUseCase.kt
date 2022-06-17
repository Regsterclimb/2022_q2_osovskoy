package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.repository.RemoteLoansRepository
import javax.inject.Inject

class GetRemoteLoansUseCase @Inject constructor(private val remoteLoansRepository: RemoteLoansRepository) {

    suspend operator fun invoke(): List<Loan> = remoteLoansRepository.getAll()
}