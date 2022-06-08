package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.repository.LoansRepository
import javax.inject.Inject

class GetLoanConditionUseCase @Inject constructor(private val loansRepository: LoansRepository) {

    suspend operator fun invoke(): LoanCondition = loansRepository.getLoanCondition()
}