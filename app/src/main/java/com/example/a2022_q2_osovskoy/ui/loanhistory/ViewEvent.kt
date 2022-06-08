package com.example.a2022_q2_osovskoy.ui.loanhistory

sealed class HistoryViewEvent {
    object SwipeRefresh : HistoryViewEvent()
}
