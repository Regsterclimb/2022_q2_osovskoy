package com.example.a2022_q2_osovskoy.presentation.loancondition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoanConditionUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateAppConfigUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanConditionViewModel @Inject constructor(
    private val updateAppConfigUseCase: UpdateAppConfigUseCase,
    private val getLoanConditionUseCase: GetLoanConditionUseCase,
) : ViewModel() {

    private val _loanConditionValue = MutableLiveData<LoanConditionState>()
    val loanCondition = _loanConditionValue

    fun updateAppConfig(appConfig: AppConfig) {
            updateAppConfigUseCase(appConfig)
    }

    init {
        viewModelScope.launch {
            _loanConditionValue.value = handleResultState(getLoanConditionUseCase())
        }
    }

    private fun handleResultState(result: ResultState<LoanCondition>): LoanConditionState =
        when (result) {
            is ResultState.Success -> LoanConditionState.Success(result.data)
            is ResultState.Error -> LoanConditionState.Error
        }
}