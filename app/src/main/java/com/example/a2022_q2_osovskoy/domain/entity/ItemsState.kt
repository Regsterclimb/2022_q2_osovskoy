package com.example.a2022_q2_osovskoy.domain.entity

sealed class ItemsState {
    class Success(val result: List<ListItem>) : ItemsState()
    object Error : ItemsState()
}