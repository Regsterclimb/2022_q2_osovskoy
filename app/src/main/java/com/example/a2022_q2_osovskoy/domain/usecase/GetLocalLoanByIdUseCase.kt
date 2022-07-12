package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import com.example.a2022_q2_osovskoy.domain.repository.LocalLoansRepository
import javax.inject.Inject

class GetLocalLoanByIdUseCase @Inject constructor(private val localLoansRepository: LocalLoansRepository) {

    suspend operator fun invoke(loanId: Long): LoanDetail = localLoansRepository.getById(loanId)

}