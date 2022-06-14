package com.example.a2022_q2_osovskoy.presentation.loanhistory

import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan

sealed class LoansState {
    class Success(val loans: List<Loan>) : LoansState()
    sealed class Error : LoansState() {
        object BadRequest : Error() // такой пользователь уже найден
        object Unauthorized : Error() // неавторизованный пользователь
        object Forbidden : Error() // означает ограничение или отсутствие доступа к материалу на странице, которую вы пытаетесь загрузить.
        object NotFound : Error() // пусто
        object ServerIsNotResponding : Error() //сервер не отвечает
    }

    object Empty : LoansState()
    object Loading : LoansState()
}