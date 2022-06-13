package com.example.a2022_q2_osovskoy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.usecase.GetAppConfigUseCase
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.sample.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class SupportViewModel @Inject constructor(private val getAppConfigUseCase: GetAppConfigUseCase) :
    ViewModel() {

    private val _appConfigState = SingleLiveEvent<AppConfigEvent>()
    val appAppConfigEvent: LiveData<AppConfigEvent> = _appConfigState

    init {
        viewModelScope.launch {
            _appConfigState.value = handleAppConfig(getAppConfigUseCase())
        }
    }
    //todo() тесты
    private fun handleAppConfig(config: AppConfig): AppConfigEvent = when (config) {
        AppConfig.UNAUTHORIZED -> AppConfigEvent.NavigateToRegistration(NavDestination.DEEP_REGISTRATION)
        AppConfig.UNINSTRUCTED -> AppConfigEvent.NavigateToLoanRequest(NavDestination.DEEP_LOAN_REQUEST)
        else -> AppConfigEvent.NavigateToHistory(NavDestination.DEEP_HISTORY)
    }
}