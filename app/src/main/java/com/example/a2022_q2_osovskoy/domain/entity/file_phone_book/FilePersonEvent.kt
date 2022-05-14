package com.example.a2022_q2_osovskoy.domain.entity.file_phone_book

import com.example.a2022_q2_osovskoy.domain.entity.Person

sealed class FilePersonEvent {
    class Success(val result: List<Person>) : FilePersonEvent()
    object Loading : FilePersonEvent()
    object Error : FilePersonEvent()
    object Empty : FilePersonEvent()
}
