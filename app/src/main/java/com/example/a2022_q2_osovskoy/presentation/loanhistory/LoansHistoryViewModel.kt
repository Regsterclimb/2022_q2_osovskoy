package com.example.a2022_q2_osovskoy.presentation.loanhistory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
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
            _loansState.value = handleResultState(getLoansUseCase())
        }
    }

    private fun handleResultState(state: ResultState<List<Loan>>): LoansState = when (state) {
        is ResultState.Success -> {
            if (state.data != null) {
                LoansState.Success(loans = state.data)
            } else {
                LoansState.Empty
            }
        }
        is ResultState.Error -> LoansState.Error.BadRequest /*handleError(state.error ?: NullPointerException())*/
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