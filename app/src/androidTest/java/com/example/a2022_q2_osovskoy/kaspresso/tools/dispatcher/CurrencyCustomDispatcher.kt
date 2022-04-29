package com.example.a2022_q2_osovskoy.kaspresso.tools.dispatcher

import android.content.Context
import android.util.Log
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

interface CurrencyCustomDispatcher {

    fun readStringFromFile(fileName: String): String

    class Base(private val appContext: Context) : Dispatcher(),
        CurrencyCustomDispatcher {

        override fun dispatch(request: RecordedRequest): MockResponse =
            when (request.path) {
                "/daily_json.js" -> {
                    Log.d("TestActivity", "setResponses")
                    MockResponse()
                        .setResponseCode(200)
                        .setBody(readStringFromFile("success_response.json"))
                }
                else -> {
                    MockResponse().setResponseCode(404)
                }
            }

        override fun readStringFromFile(fileName: String): String =
            try {
                appContext.assets.open(fileName).bufferedReader().readText()
            } catch (e: Exception) {
                Log.e("CurrencyCustomDispatcher", "readStringFromFile error")
                throw Exception()
            }
    }
}
