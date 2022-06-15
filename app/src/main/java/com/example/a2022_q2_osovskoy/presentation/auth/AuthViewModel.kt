package com.example.a2022_q2_osovskoy.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.usecase.auth.LogInUseCase
import com.example.a2022_q2_osovskoy.presentation.sample.SingleLiveEvent
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val logInUseCase: LogInUseCase) : ViewModel() {

    private val _authState = SingleLiveEvent<AuthState>()
    val authState: LiveData<AuthState> = _authState

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

    private fun executeAuthRequest(name: String, password: String) {
        viewModelScope.launch {
            val baseUser = BaseUser(name, password)
            _authState.value = AuthState.Loading
            logInUseCase(baseUser)
            _authState.value =AuthState.Success
        }
    }

    private fun handleErrors(exception: Throwable) {
        when (exception) {
            is BadRequestException -> _authState.value = AuthState.Error.BadRequest
            is UnauthorizedException -> _authState.value = AuthState.Error.Unauthorized
            is ForbiddenException -> _authState.value = AuthState.Error.Forbidden
            is NotFoundException -> _authState.value = AuthState.Error.NotFound
            is ServerIsNotRespondingException -> _authState.value =
                AuthState.Error.ServerIsNotResponding
        }
    }
}