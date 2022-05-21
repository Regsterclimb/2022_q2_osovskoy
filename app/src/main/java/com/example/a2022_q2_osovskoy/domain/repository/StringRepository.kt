package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.MockedString

interface StringRepository {

    fun getFromRemote(): MockedString

    fun getFromLocal(): MockedString
}