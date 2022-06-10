package com.example.a2022_q2_osovskoy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.usecase.GetAppConfigUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SupportViewModel @Inject constructor(private val getAppConfigUseCase: GetAppConfigUseCase) :
    ViewModel() {

    private val _appConfigState = MutableLiveData<ConfigState>()
    val appConfigState: LiveData<ConfigState> = _appConfigState

    init {
        viewModelScope.launch {
            _appConfigState.value = handleAppConfig(getAppConfigUseCase())
        }
    }

    private fun handleAppConfig(config: AppConfig): ConfigState = when (config) {
        AppConfig.UNAUTHORIZED -> ConfigState.Unauthorized
        AppConfig.AUTHORIZED -> ConfigState.Authorized
        AppConfig.UNINSTRUCTED -> ConfigState.Uninstructed
        else -> ConfigState.Base
    }
}