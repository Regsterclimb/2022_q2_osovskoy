package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.repository.RemoteLoansRepository
import javax.inject.Inject

class RequestLoanUseCase @Inject constructor(private val remoteLoansRepository: RemoteLoansRepository) {

    suspend operator fun invoke(loanRequest: LoanRequest): Loan =
        remoteLoansRepository.requestLoan(loanRequest)
}