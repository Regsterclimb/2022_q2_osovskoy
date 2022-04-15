package com.example.a2022_q2_osovskoy.data.remote.network_module

import com.example.a2022_q2_osovskoy.data.remote.responses.ApiData

interface NetworkModuleResponses {
    suspend fun getAllData(): ApiData
}