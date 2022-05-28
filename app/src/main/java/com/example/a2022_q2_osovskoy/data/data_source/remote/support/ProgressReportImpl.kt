package com.example.a2022_q2_osovskoy.data.data_source.remote.support

import com.example.a2022_q2_osovskoy.domain.entity.ProgressResult
import javax.inject.Inject

class ProgressReportImpl @Inject constructor() : ProgressReport {

    private var progressResult: Int = 0

    override fun putReport(progress: Int) {
        progressResult = progress
    }

    override fun getReport(): ProgressResult = when (progressResult) {
        100 -> {
            progressResult = 0
            ProgressResult.Success
        }
        in 0..100 -> ProgressResult.Loading(progressValue = progressResult)
        else -> {
            progressResult = 0
            ProgressResult.Error
        }
    }
}