package com.example.a2022_q2_osovskoy.data.datasourse.local

interface TokenDataSource {

    fun get() : String

    suspend fun update(token:String)
}