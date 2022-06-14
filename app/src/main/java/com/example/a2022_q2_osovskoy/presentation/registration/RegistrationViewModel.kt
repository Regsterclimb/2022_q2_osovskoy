package com.example.a2022_q2_osovskoy.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.usecase.auth.RegisterUseCase
import com.example.a2022_q2_osovskoy.extentions.BadRequestException
import com.example.a2022_q2_osovskoy.presentation.sample.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {

    private val _regState = SingleLiveEvent<RegEvent>()
    val regEvent: LiveData<RegEvent> = _regState

    private val handler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is BadRequestException -> _regState.value = RegEvent.Error.BadRequest
        }
    }

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
        viewModelScope.launch(handler) {
            _regState.value = RegEvent.Loading
            registerUseCase(BaseUser(name, password))
            _regState.value = RegEvent.Success
        }
    }

    /*private fun getRegResult(regResult: ResultState<Unit>): RegEvent = when (regResult) {
        is ResultState.Success -> RegEvent.Success
        is ResultState.Error -> RegEvent.Error.BadRequest
    }*/
}