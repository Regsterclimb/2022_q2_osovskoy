package com.example.a2022_q2_osovskoy.domain.entity.file_phone_book

sealed class FileFirstEvent {
    object Loading : FileFirstEvent()
    object Success : FileFirstEvent()
    object Error : FileFirstEvent()
}
