package com.example.a2022_q2_osovskoy.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.usecase.auth.RegisterUseCase
import com.example.a2022_q2_osovskoy.utils.sample.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {

    private val _regState = SingleLiveEvent<RegEvent>()
    val regEvent: LiveData<RegEvent> = _regState

    fun tryReg(name: String, password: String) {
        when {
            name.isEmpty() -> {
                _regState.value = RegEvent.InputError.Name
            }
            password.isEmpty() -> {
                _regState.value = RegEvent.InputError.Password
            }
            else -> {
                executeRegRequest(name, password)
            }
        }
    }

    private fun executeRegRequest(name: String, password: String) {
        viewModelScope.launch {
            _regState.value = RegEvent.Loading
            _regState.value = getRegResult(registerUseCase(BaseUser(name, password)))
        }
    }

    private fun getRegResult(regResult: ResultState<Unit>): RegEvent = when (regResult) {
        is ResultState.Success -> RegEvent.Success
        is ResultState.Error -> RegEvent.Error
    }
}