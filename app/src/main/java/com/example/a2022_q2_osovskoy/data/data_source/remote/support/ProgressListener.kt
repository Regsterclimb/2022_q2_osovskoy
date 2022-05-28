package com.example.a2022_q2_osovskoy.data.data_source.remote.support

interface ProgressListener {
    fun onRequestProgress(bytesWritten: Long, contentLength: Long)
}