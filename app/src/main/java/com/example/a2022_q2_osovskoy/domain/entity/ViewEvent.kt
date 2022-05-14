package com.example.a2022_q2_osovskoy.domain.entity

sealed class ViewEvent {
    class Typing(val text: String) : ViewEvent()
    object Click : ViewEvent()
}