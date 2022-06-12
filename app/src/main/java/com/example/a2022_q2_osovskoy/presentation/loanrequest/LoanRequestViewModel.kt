package com.example.a2022_q2_osovskoy.presentation.loanrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.RequestLoanUseCase
import com.example.a2022_q2_osovskoy.utils.sample.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanRequestViewModel @Inject constructor(
    private val requestLoanUseCase: RequestLoanUseCase,
) : ViewModel() {

    private val _loanRequestState = SingleLiveEvent<LoanRequestState>()
    val loanRequestState: LiveData<LoanRequestState> = _loanRequestState

    fun handleLoanCondition(amount: Long, percent: String, period: Int) {
        _loanRequestState.value =
            LoanRequestState.LoanConditionReceieved(LoanCondition(period, amount, percent.toDouble()))
    }

    fun trySendRequest(
        amount: Long,
        percent: Double,
        period: Int,
        name: String,
        lastName: String,
        phone: String,
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
            name.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty() -> {
                sendLoanRequest(amount, name, lastName, phone, percent, period)
            }
            else -> LoanRequestState.Error
        }
    }

    private fun sendLoanRequest(
        amount: Long,
        name: String,
        lastName: String,
        phone: String,
        percent: Double,
        period: Int,
    ) {
        viewModelScope.launch {
            _loanRequestState.value = LoanRequestState.Loading
            _loanRequestState.value = handleLoanResultRequest(
                requestLoanUseCase(
                    LoanRequest(
                        amount = amount,
                        firstName = name,
                        lastName = lastName,
                        percent = percent,
                        period = period,
                        phoneNumber = phone
                    )
                )
            )
        }
    }

    private fun handleLoanResultRequest(result: ResultState<Loan>): LoanRequestState =
        when (result) {
            is ResultState.Success -> LoanRequestState.Success(result.data)
            is ResultState.Error -> LoanRequestState.Error
        }

}