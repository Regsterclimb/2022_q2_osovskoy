package com.example.a2022_q2_osovskoy.data.data_source.remote.support

import com.example.a2022_q2_osovskoy.domain.entity.ProgressResult

interface ProgressReport {

    fun putReport(progress: Int)

    fun getReport(): ProgressResult
}