package com.example.a2022_q2_osovskoy.presentation.loanrequest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.LoanRequest
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.RequestLoanUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

class LoanRequestViewModel @Inject constructor(
    private val requestLoanUseCase: RequestLoanUseCase,
) : ViewModel() {

    private val _loanRequestState = MutableLiveData<LoanRequestState>()
    val loanRequestState: LiveData<LoanRequestState> = _loanRequestState

    private val _loanCondition = MutableLiveData(LoanCondition(0, 0, 0.0))
    val loanCondition: LiveData<LoanCondition> = _loanCondition

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleErrorState(throwable)
    }

    private fun handleErrorState(exception: Throwable) {
        _loanRequestState.value = when (exception) {
            is BadRequestException -> LoanRequestState.Error.BadRequest
            is UnauthorizedException -> LoanRequestState.Error.Unauthorized
            is ForbiddenException -> LoanRequestState.Error.Forbidden
            is NotFoundException -> LoanRequestState.Error.NotFound
            is ServerIsNotRespondingException -> LoanRequestState.Error.ServerIsNotResponding
            is IOException -> LoanRequestState.Error.NoInternetConnection
            else -> LoanRequestState.Error.Unknown
        }
    }

    fun setTyping() {
        _loanRequestState.value = LoanRequestState.Typing
    }

    fun setLoanCondition(amount: Long, percent: String, period: Int) {
        _loanCondition.value = LoanCondition(
            period,
            amount,
            percent.toDouble())
    }

    fun trySendRequest(
        loanCondition:LoanCondition,
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
                sendRequest( name, lastName, phone,loanCondition)
            }
            else -> LoanRequestState.Error.Unknown
        }
    }

    private fun sendRequest(
        name: String,
        lastName: String,
        phone: String,
        loanCondition: LoanCondition,
    ) {
        viewModelScope.launch(handler) {
            _loanRequestState.value = LoanRequestState.Loading
            _loanRequestState.value = LoanRequestState.Success(
                requestLoanUseCase(
                    LoanRequest(
                        amount = loanCondition.maxAmount,
                        firstName = name,
                        lastName = lastName,
                        percent = loanCondition.percent,
                        period = loanCondition.period,
                        phoneNumber = phone
                    )
                )
            )
        }
    }
}