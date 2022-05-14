package com.example.a2022_q2_osovskoy.domain.entity.base_phone_book

import com.example.a2022_q2_osovskoy.domain.entity.Person

sealed class PersonEvent {
    class Success(val result: List<Person>) : PersonEvent()
    object Loading : PersonEvent()
    object Error : PersonEvent()
    object Empty : PersonEvent()
}
