package com.example.a2022_q2_osovskoy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.usecase.GetAppConfigUseCase
import com.example.a2022_q2_osovskoy.presentation.sample.SingleLiveEvent
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import kotlinx.coroutines.launch
import javax.inject.Inject

class SupportViewModel @Inject constructor(private val getAppConfigUseCase: GetAppConfigUseCase) :
    ViewModel() {

    private val _appConfigState = SingleLiveEvent<StartScreenEvent>()
    val appStartScreenEvent: LiveData<StartScreenEvent> = _appConfigState

    init {
        viewModelScope.launch {
            _appConfigState.value = handleAppConfig(getAppConfigUseCase())
        }
    }

    private fun handleAppConfig(config: AppConfig): StartScreenEvent = when (config) {
        AppConfig.UNAUTHORIZED -> StartScreenEvent.NavigateToRegistration(NavDestination.DEEP_REGISTRATION)
        AppConfig.UNINSTRUCTED -> StartScreenEvent.NavigateToLoanRequest(NavDestination.DEEP_LOAN_REQUEST)
        else -> StartScreenEvent.NavigateToHistory(NavDestination.DEEP_HISTORY)
    }
}