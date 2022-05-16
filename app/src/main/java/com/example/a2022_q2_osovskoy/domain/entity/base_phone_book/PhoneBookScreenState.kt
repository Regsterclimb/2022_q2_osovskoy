package com.example.a2022_q2_osovskoy.domain.entity.base_phone_book

sealed class PhoneBookScreenState {
    object File : PhoneBookScreenState()
    object Database : PhoneBookScreenState()
    object Error : PhoneBookScreenState()
}
