package com.example.a2022_q2_osovskoy.domain.entity

sealed class SecondScreenModelState {
    object Success : SecondScreenModelState()
    object WrongTextInput : SecondScreenModelState()
    object ExitFromScreen : SecondScreenModelState()
}
