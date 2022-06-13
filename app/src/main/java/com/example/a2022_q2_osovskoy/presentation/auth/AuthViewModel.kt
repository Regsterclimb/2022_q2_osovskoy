package com.example.a2022_q2_osovskoy.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.usecase.auth.LogInUseCase
import com.example.a2022_q2_osovskoy.utils.sample.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val logInUseCase: LogInUseCase) : ViewModel() {

    private val _authState = SingleLiveEvent<AuthEvent>()
    val authEvent: LiveData<AuthEvent> = _authState

    fun tryAuth(name: String, password: String) {
        when {
            name.isEmpty() -> {
                _authState.value = AuthEvent.InputError.Name
            }
            password.isEmpty() -> {
                _authState.value = AuthEvent.InputError.Password
            }
            else -> {
                executeAuthRequest(name, password)
            }
        }
    }

    private fun executeAuthRequest(name: String, password: String) {
        viewModelScope.launch {
            val baseUser = BaseUser(name, password)
            _authState.value = AuthEvent.Loading
            _authState.value = getAuthResult(logInUseCase(baseUser))
        }
    }

    private fun getAuthResult(authResult: ResultState<Unit>): AuthEvent = when (authResult) {
        is ResultState.Success -> AuthEvent.Success
        is ResultState.Error -> AuthEvent.Error
    }
}