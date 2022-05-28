package com.example.a2022_q2_osovskoy.presentation

import com.example.a2022_q2_osovskoy.domain.entity.FilePostInfo

sealed class MainActivityModelState {
    object FileLoading : MainActivityModelState()
    class FileLoadingSuccess(val filePostInfo: FilePostInfo) : MainActivityModelState()
    object FileLoadingErrorIO : MainActivityModelState()
    object FileLoadingErrorHttp : MainActivityModelState()
}
