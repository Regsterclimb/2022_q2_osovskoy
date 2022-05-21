package com.example.a2022_q2_osovskoy.ui.second_screen

sealed class SecondScreenViewState {
    class ClickOnAcceptButton(val textInput: String) : SecondScreenViewState()
    object ExitFromScreen: SecondScreenViewState()
}