package com.example.a2022_q2_osovskoy.data.data_source.remote.support

import kotlinx.coroutines.flow.SharedFlow

interface ProgressReport {

    suspend fun putReport(progress: Int)

    suspend fun getReport(): SharedFlow<Int>
}