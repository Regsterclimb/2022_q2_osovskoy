package com.example.a2022_q2_osovskoy.domain.entity.add_person

sealed class FieldsState {
    object Error : FieldsState()
    object Empty : FieldsState()
    class Success(val name: String, val number: String) : FieldsState()
}
