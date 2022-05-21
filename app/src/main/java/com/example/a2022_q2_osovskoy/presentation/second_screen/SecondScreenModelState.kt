package com.example.a2022_q2_osovskoy.presentation.second_screen

sealed class SecondScreenModelState {
    object Success : SecondScreenModelState()
    object WrongTextInput : SecondScreenModelState()
    object ExitFromScreen : SecondScreenModelState()
}
