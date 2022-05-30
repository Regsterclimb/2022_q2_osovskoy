package com.example.a2022_q2_osovskoy.data.data_source.remote.support

import javax.inject.Inject

class ProgressListenerImpl @Inject constructor(private val progressReport: ProgressReport) :
    ProgressListener {

    override suspend fun onRequestProgress(bytesWritten: Long, contentLength: Long) {
        if (contentLength != -1L) {
            progressReport.putReport((100 * bytesWritten / contentLength).toInt())
        }
    }
}