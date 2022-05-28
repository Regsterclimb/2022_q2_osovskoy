package com.example.a2022_q2_osovskoy.data.data_source.remote.support

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*

class CounterRequestBody(
    private val requestBody: RequestBody,
    private val progressListener: ProgressListener,
) : RequestBody() {

    override fun contentType(): MediaType? = requestBody.contentType()

    override fun contentLength(): Long {
        try {
            return requestBody.contentLength()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return -1
    }

    override fun writeTo(sink: BufferedSink) {
        val bufferedSink: BufferedSink = CountingSink(sink).buffer()
        requestBody.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    inner class CountingSink(sink: Sink) : ForwardingSink(sink) {
        private var bytesWritten: Long = 0

        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)
            bytesWritten += byteCount
            progressListener.onRequestProgress(bytesWritten, contentLength())
        }
    }
}