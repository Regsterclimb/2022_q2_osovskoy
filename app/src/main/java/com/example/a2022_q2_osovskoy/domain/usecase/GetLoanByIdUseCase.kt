package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.repository.RemoteLoansRepository
import javax.inject.Inject

class GetLoanByIdUseCase @Inject constructor(private val remoteLoansRepository: RemoteLoansRepository) {

    suspend operator fun invoke(loanId: Long): Loan = remoteLoansRepository.getLoanById(loanId)
}