package com.example.a2022_q2_osovskoy.data.data_source.remote.support

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import javax.inject.Inject

class ProgressInterceptor @Inject constructor(private val progressListener: ProgressListener) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(wrapRequest(chain.request(), progressListener))

    private fun wrapRequest(request: Request, progressListener: ProgressListener): Request {
        val requestBody: RequestBody = try {
            request.body!!
        } catch (e: NullPointerException) {
            throw e
        }
        return request.newBuilder().post(CounterRequestBody(requestBody, progressListener))
            .build()
    }
}

