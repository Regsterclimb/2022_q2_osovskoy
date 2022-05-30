package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.data.model.ResultState
import kotlinx.coroutines.flow.SharedFlow

interface FileRepository {

    suspend fun uploadFile(): ResultState

    suspend fun getProgress(): SharedFlow<Int>
}