package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import com.example.a2022_q2_osovskoy.domain.repository.RemoteLoansRepository
import javax.inject.Inject

class GetRemoteLoanByIdUseCase @Inject constructor(private val remoteLoansRepository: RemoteLoansRepository) {

    suspend operator fun invoke(loanId: Long): LoanDetail =
        remoteLoansRepository.getLoanById(loanId)
}