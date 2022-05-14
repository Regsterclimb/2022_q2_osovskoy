package com.example.a2022_q2_osovskoy.domain.entity

sealed class ViewColorState {
    class Success(val color: Int) : ViewColorState()
    object Error : ViewColorState()
}