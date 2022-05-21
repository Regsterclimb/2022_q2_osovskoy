package com.example.a2022_q2_osovskoy.presentation.viewmodel

import com.example.a2022_q2_osovskoy.domain.entity.ListItem

sealed class ItemsState {
    class Success(val result: List<ListItem>) : ItemsState()
    object Error : ItemsState()
}