package com.example.a2022_q2_osovskoy.data.data_source.remote.support

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class ProgressReportImpl @Inject constructor() : ProgressReport {

    private var progressResult: Int = 0

    private var progressFlow = MutableSharedFlow<Int>()

    override suspend fun putReport(progress: Int) {
        progressFlow.emit(progress)
    }

    override suspend fun getReport(): SharedFlow<Int> = progressFlow.asSharedFlow()
}