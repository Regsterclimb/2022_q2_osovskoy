package com.example.a2022_q2_osovskoy.presentation.loandetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import com.example.a2022_q2_osovskoy.domain.usecase.GetLocalLoanByIdUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.GetRemoteLoanByIdUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

class LoanDetailViewModel @Inject constructor(
    private val getRemoteLoanByIdUseCase: GetRemoteLoanByIdUseCase,
    private val getLocalLoanByIdUseCase: GetLocalLoanByIdUseCase,
) : ViewModel() {

    private val _loanDetailState = MutableLiveData<LoanDetailState>()
    val loanState = _loanDetailState

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleErrors(throwable)
    }

    private fun handleErrors(exception: Throwable) {
        _loanDetailState.value = when (exception) {
            is BadRequestException -> LoanDetailState.Error.BadRequest
            is UnauthorizedException -> LoanDetailState.Error.Unauthorized
            is ForbiddenException -> LoanDetailState.Error.Forbidden
            is NotFoundException -> LoanDetailState.Error.NotFound
            is ServerIsNotRespondingException -> LoanDetailState.Error.ServerNotResponding
            is IOException -> LoanDetailState.Error.NoInternetConnection
            else -> LoanDetailState.Error.Unknown
        }
    }

    fun getLocalLoan(loanId: Long) {
        viewModelScope.launch(handler) {
            setLoading()
            val detail = getLocalLoanByIdUseCase(loanId)
            _loanDetailState.value = LoanDetailState.Success.Local(detail)
            setApprove(detail)
        }
    }

    fun getRemoteLoan(loanId: Long) {
        viewModelScope.launch(handler) {
            setLoading()
            val detail = getRemoteLoanByIdUseCase(loanId)
            _loanDetailState.value = LoanDetailState.Success.Remote(detail)
            setApprove(detail)
        }
    }

    private fun setLoading() {
        _loanDetailState.value = LoanDetailState.Loading
    }

    private fun setApprove(loanDetail: LoanDetail) {
        if (loanDetail.state == "APPROVED") {
            _loanDetailState.value = LoanDetailState.Approved(isApproved = true)
        } else {
            _loanDetailState.value = LoanDetailState.Approved(isApproved = false)
        }
    }
}