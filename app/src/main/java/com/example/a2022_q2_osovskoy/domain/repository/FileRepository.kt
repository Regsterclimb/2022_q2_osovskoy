package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.data.model.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.ProgressResult

interface FileRepository {

    suspend fun uploadFile(): ResultState

    fun getProgress(): ProgressResult
}