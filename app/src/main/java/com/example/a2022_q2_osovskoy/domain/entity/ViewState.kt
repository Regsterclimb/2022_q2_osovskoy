package com.example.a2022_q2_osovskoy.domain.entity

sealed class ViewState {
    class Success(val list: List<Person>) : ViewState()
    object Empty : ViewState()
    object Loading : ViewState()
    object Error : ViewState()
}
