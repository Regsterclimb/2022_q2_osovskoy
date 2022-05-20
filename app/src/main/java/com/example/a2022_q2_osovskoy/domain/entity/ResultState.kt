package com.example.a2022_q2_osovskoy.domain.entity

sealed class ResultState {
    class Success(val result: List<ListItem>) : ResultState()
    object Error : ResultState()
}