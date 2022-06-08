package com.example.a2022_q2_osovskoy.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.usecase.GetAppConfigUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class SupportViewModel @Inject constructor(private val getAppConfigUseCase: GetAppConfigUseCase) :
    ViewModel() {

    private val _appConfigState = MutableLiveData<Boolean>()
    val appConfigState: LiveData<Boolean> = _appConfigState

    init {
        viewModelScope.launch {
            _appConfigState.value = getAppConfigUseCase().isLogged
        }
    }
}