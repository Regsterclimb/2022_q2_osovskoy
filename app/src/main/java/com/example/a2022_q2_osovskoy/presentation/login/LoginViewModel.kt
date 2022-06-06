package com.example.a2022_q2_osovskoy.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.LoginState
import com.example.a2022_q2_osovskoy.domain.usecase.LogInUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    private val _loginState = MutableLiveData<LoginModelState>()
    val loginState: LiveData<LoginModelState> = _loginState

    fun startLogin(baseUser: BaseUser) {
        viewModelScope.launch {
            _loginState.value = when (logInUseCase(baseUser)) {
                is LoginState.Success -> LoginModelState.Succeed
                is LoginState.Error -> LoginModelState.Error
            }
        }
    }

    fun startRegistration(baseUser: BaseUser) {
        viewModelScope.launch {
            _loginState.value = when (registerUseCase(baseUser)) {
                is LoginState.Success -> LoginModelState.Succeed
                is LoginState.Error -> LoginModelState.Error
            }
        }
    }
}