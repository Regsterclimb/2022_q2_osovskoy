package com.example.a2022_q2_osovskoy.domain.repository

interface StringRepository {

    fun getFromRemote(): String

    fun getFromLocal(): String
}