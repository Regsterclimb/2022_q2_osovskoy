package com.example.a2022_q2_osovskoy.data.model

import com.example.a2022_q2_osovskoy.domain.entity.FilePostInfo

sealed class ResultState {
    class Success(val postInfo: FilePostInfo) : ResultState()

    sealed class MainError : ResultState() {
        object Http : MainError()
        object IO : MainError()
    }
}