package com.example.a2022_q2_osovskoy.presentation.loanrequest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoanConditionUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateAppConfigUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

class LoanConditionViewModel @Inject constructor(
    private val updateAppConfigUseCase: UpdateAppConfigUseCase,
    private val getLoanConditionUseCase: GetLoanConditionUseCase,
) : ViewModel() {

    private val _loanConditionState = MutableLiveData<LoanConditionState>()
    val loanCondition = _loanConditionState

    private val _instructionState = MutableLiveData(Instruction(true))
    val instructionState = _instructionState

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleErrors(throwable)
    }

    init {
        refreshConditions()
    }

    fun showInstruction(show: Boolean) {
        _instructionState.value = Instruction(show)
    }

    fun updateAppConfig(appConfig: AppConfig) {
        updateAppConfigUseCase(appConfig)
    }

    fun refreshConditions() {
        viewModelScope.launch(handler) {
            _loanConditionState.value = LoanConditionState.Loading
            _loanConditionState.value = LoanConditionState.Success(getLoanConditionUseCase())
        }
    }

    private fun handleErrors(exception: Throwable) {
        _loanConditionState.value = when (exception) {
            is BadRequestException -> LoanConditionState.Error.BadRequest
            is UnauthorizedException -> LoanConditionState.Error.Unauthorized
            is ForbiddenException -> LoanConditionState.Error.Forbidden
            is NotFoundException -> LoanConditionState.Error.NotFound
            is ServerIsNotRespondingException -> LoanConditionState.Error.ServerIsNotResponding
            is IOException -> LoanConditionState.Error.NoInternetConnection
            else -> LoanConditionState.Error.Unknown
        }
    }
}