package com.example.a2022_q2_osovskoy.presentation.loanhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoansUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoansHistoryViewModel @Inject constructor(private val getLoansUseCase: GetLoansUseCase) :
    ViewModel() {

    private val _loansState = MutableLiveData<LoansState>()
    val loansState: LiveData<LoansState> = _loansState

    init {
        refreshLoans()
    }

    fun refreshLoans() {
        viewModelScope.launch {
            _loansState.value = LoansState.Success(getLoansUseCase())
        }
    }

    /*private fun handleError(e: Throwable): LoansState = when (e) {
        is BadRequestException ->
    }*/
    /*class BadRequestException : RuntimeException() // такой пользователь уже найден
class UnauthorizedException : RuntimeException() // неавторизованный пользователь
class ForbiddenException : RuntimeException() // означает ограничение или отсутствие доступа к материалу на странице, которую вы пытаетесь загрузить.

class NotFoundException : RuntimeException() // пусто
class ServerIsNotRespondingException : RuntimeException() //сервер не отвечает*/
}