package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.repository.LoansRepository
import javax.inject.Inject

class GetLoanByIdUseCase @Inject constructor(private val loansRepository: LoansRepository) {

    suspend operator fun invoke(loanId: Long):ResultState<Loan> = loansRepository.getLoanById(loanId)
}