package com.example.a2022_q2_osovskoy.presentation.loandetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoanByIdUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanDetailViewModel @Inject constructor(private val getLoanByIdUseCase: GetLoanByIdUseCase) :
    ViewModel() {

    private val _loanState = MutableLiveData<LoanDetailState>()
    val loanState = _loanState

    fun getLoan(loanId: Long) {
        viewModelScope.launch {
            _loanState.value = handleResultState(getLoanByIdUseCase(loanId))
        }
    }

    private fun handleResultState(result: ResultState<Loan>): LoanDetailState = when (result) {
        is ResultState.Success -> LoanDetailState.Success(result.data ?: Loan(1,2.2,"24","1234",8.8))
        is ResultState.Error -> LoanDetailState.Error
    }
}