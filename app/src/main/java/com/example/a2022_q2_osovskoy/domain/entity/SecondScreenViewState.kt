package com.example.a2022_q2_osovskoy.domain.entity

sealed class SecondScreenViewState {
    class ClickOnAcceptButton(val textInput: String) : SecondScreenViewState()
    object ExitFromScreen:SecondScreenViewState()
}