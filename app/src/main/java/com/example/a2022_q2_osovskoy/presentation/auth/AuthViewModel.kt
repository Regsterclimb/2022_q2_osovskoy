package com.example.a2022_q2_osovskoy.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.AuthResult
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoginConfigUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.LogInUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.RegisterUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.UpdateLoginConfigUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val registerUseCase: RegisterUseCase,
    private val getLoginConfigUseCase: GetLoginConfigUseCase,
    private val updateLoginConfigUseCase: UpdateLoginConfigUseCase,
) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val _loginScreenState = MutableLiveData<LoginScreenState>(LoginScreenState.UnAuthorized)
    val loginScreenState: LiveData<LoginScreenState> = _loginScreenState

    init {
        viewModelScope.launch {
            setLoginScreenState()
        }
    }

    //todo() buisness logic
    fun tryAuth(name: String, password: String) {
        when {
            name.isEmpty() -> {
                _authState.value = AuthState.InputError.Name
            }
            password.isEmpty() -> {
                _authState.value = AuthState.InputError.Password
            }
            else -> {
                executeAuthRequest(name, password)
            }
        }
    }

    //todo()
    private fun executeAuthRequest(name: String, password: String) {
        viewModelScope.launch {
            val baseUser = BaseUser(name, password)
            _authState.value = if (loginScreenState.value is LoginScreenState.Registration) {
                getAuthResult(registerUseCase(baseUser))
            } else {
                getAuthResult(logInUseCase(baseUser))
            }
        }
    }

    fun changeLoginScreenState(setLoginScreen: Boolean) {
        viewModelScope.launch {
            updateLoginConfigUseCase(LoginConfigState(setLoginScreen))
            setLoginScreenState()
        }
    }

    private suspend fun setLoginScreenState() {
        _loginScreenState.value =
            if (getLoginConfigUseCase().isScreenLogin) {
                LoginScreenState.UnAuthorized
            } else {
                LoginScreenState.Registration
            }
    }


    private fun getAuthResult(authResult: AuthResult): AuthState = when (authResult) {
        is AuthResult.Registration.Success -> AuthState.Registration.Success
        is AuthResult.Registration.Error -> AuthState.Registration.Error
        is AuthResult.Login.Success -> AuthState.Login.Success
        is AuthResult.Login.Error -> AuthState.Login.Error
    }
}