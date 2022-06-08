package com.example.a2022_q2_osovskoy.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoanConditionUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateAppConfigUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val updateAppConfigUseCase: UpdateAppConfigUseCase,
    private val getLoanConditionUseCase: GetLoanConditionUseCase,
) : ViewModel() {

    private val _loanConditionValue = MutableLiveData<LoanCondition>()
    val loanCondition = _loanConditionValue

    fun changeAppConfig(isUserLoggedIn: Boolean) {
        viewModelScope.launch {
            updateAppConfigUseCase(AppConfigValue(isUserLoggedIn))
        }
    }


    init {
        viewModelScope.launch {
            _loanConditionValue.value = getLoanConditionUseCase()
        }
    }
}