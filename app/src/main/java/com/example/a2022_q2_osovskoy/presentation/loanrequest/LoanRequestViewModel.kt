package com.example.a2022_q2_osovskoy.presentation.loanrequest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.usecase.RequestLoanUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanRequestViewModel @Inject constructor(
    private val requestLoanUseCase: RequestLoanUseCase,
) : ViewModel() {

    private val _loanRequestState = MutableLiveData<LoanRequestState>()
    val loanRequestState: LiveData<LoanRequestState> = _loanRequestState


    //todo() buisness logic
    fun trySendRequest(
        amount: String,
        name: String,
        lastName: String,
        phone: String,
        percent: String,
        period: String,
    ) {
        when {
            name.isEmpty() -> {
                _loanRequestState.value = LoanRequestState.InputError.Name
            }
            lastName.isEmpty() -> {
                _loanRequestState.value = LoanRequestState.InputError.LastName
            }
            phone.isEmpty() -> {
                _loanRequestState.value = LoanRequestState.InputError.Phone
            }
            else -> {
                sendLoanRequest(amount, name, lastName, phone, percent, period)
            }
        }
    }

    //todo()
    private fun sendLoanRequest(
        amount: String,
        name: String,
        lastName: String,
        phone: String,
        percent: String,
        period: String,
    ) {
        viewModelScope.launch {
            val loanRequest = LoanRequest(amount.toLong(),
                name,
                lastName,
                percent.toDouble(),
                period.toInt(),
                phone)
            val requestedLoan = handleResultRequest(requestLoanUseCase(loanRequest))
            Log.d("LoanRequestViewModel", requestedLoan.toString())
        }
    }

    private fun handleResultRequest(result: ResultState<Loan>): LoanRequestState =
        when (result) {
            is ResultState.Success -> LoanRequestState.Success(result.data)
            is ResultState.Error -> LoanRequestState.Error
        }
}