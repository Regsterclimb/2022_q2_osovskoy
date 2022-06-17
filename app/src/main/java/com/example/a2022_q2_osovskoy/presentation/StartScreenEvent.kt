package com.example.a2022_q2_osovskoy.presentation

sealed class StartScreenEvent {
    data class NavigateToRegistration(val navDest:String) : StartScreenEvent()
    data class NavigateToLoanRequest(val navDest:String) : StartScreenEvent()
    data class NavigateToHistory(val navDest:String): StartScreenEvent()
}