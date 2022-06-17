package com.example.a2022_q2_osovskoy.presentation.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.usecase.auth.LoginUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleErrors(throwable)
    }

    private fun handleErrors(exception: Throwable) {
        _authState.value = when (exception) {
            is BadRequestException -> AuthState.Error.BadRequest
            is UnauthorizedException -> AuthState.Error.Unauthorized
            is ForbiddenException -> AuthState.Error.Forbidden
            is NotFoundException -> AuthState.Error.NotFound
            is ServerIsNotRespondingException -> AuthState.Error.ServerIsNotResponding
            is IOException -> AuthState.Error.NoInternetConnection
            else -> {
                exception.printStackTrace()
                AuthState.Error.Unknown
            }
        }
    }

    fun setTyping() {
        _authState.value = AuthState.Typing
    }

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
        viewModelScope.launch(handler) {
            _authState.value = AuthState.Loading
            loginUseCase(BaseUser(name, password))
            _authState.value = AuthState.Success
        }
    }
}