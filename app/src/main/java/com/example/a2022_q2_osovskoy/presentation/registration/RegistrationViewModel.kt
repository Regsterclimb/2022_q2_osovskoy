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

class RegistrationViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {

    private val _regState = SingleLiveEvent<RegState>()
    val regState: LiveData<RegState> = _regState


    fun tryReg(name: String, password: String) {
        when {
            name.isEmpty() -> {
                _regState.value = RegState.InputError.Name
            }
            password.isEmpty() -> {
                _regState.value = RegState.InputError.Password
            }
            else -> {
                executeRegRequest(name, password)
            }
        }
    }

    private fun executeRegRequest(name: String, password: String) {
        viewModelScope.launch {
            val baseUser = BaseUser(name, password)
            _regState.value = RegState.Loading
            _regState.value = getRegResult(registerUseCase(baseUser))
        }
    }

    private fun getRegResult(regResult: ResultState<Unit>): RegState = when (regResult) {
        is ResultState.Success -> RegState.Success
        is ResultState.Error -> RegState.Error
    }
}