package com.example.a2022_q2_osovskoy.presentation

sealed class AppConfigEvent {
    data class NavigateToRegistration(val navDest:String) : AppConfigEvent()
    data class NavigateToLoanRequest(val navDest:String) : AppConfigEvent()
    data class NavigateToHistory(val navDest:String): AppConfigEvent()
}