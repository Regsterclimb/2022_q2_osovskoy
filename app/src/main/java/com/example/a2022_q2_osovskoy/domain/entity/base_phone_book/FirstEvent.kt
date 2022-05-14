package com.example.a2022_q2_osovskoy.domain.entity.base_phone_book

sealed class FirstEvent {
    object Loading : FirstEvent()
    object Success : FirstEvent()
    object Error : FirstEvent()
}
