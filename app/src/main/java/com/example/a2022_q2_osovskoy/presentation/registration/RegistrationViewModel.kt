package com.example.a2022_q2_osovskoy.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.usecase.auth.RegisterUseCase
import com.example.a2022_q2_osovskoy.utils.exceptions.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) :
    ViewModel() {

    private val _regState = MutableLiveData<RegState>()
    val regState: LiveData<RegState> = _regState

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleErrors(throwable)
    }

    private fun handleErrors(exception: Throwable) {
        _regState.value = when (exception) {
            is BadRequestException -> RegState.Error.BadRequest
            is UnauthorizedException -> RegState.Error.Unauthorized
            is ForbiddenException -> RegState.Error.Forbidden
            is NotFoundException -> RegState.Error.NotFound
            is ServerIsNotRespondingException -> RegState.Error.ServerIsNotResponding
            is IOException -> RegState.Error.NoInternetConnection
            else -> RegState.Error.Unknown
        }
    }

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
        viewModelScope.launch(handler) {
            _regState.value = RegState.Loading
            registerUseCase(BaseUser(name, password))
            _regState.value = RegState.Success
        }
    }

    fun setTyping() {
        _regState.value = RegState.Typing
    }
}