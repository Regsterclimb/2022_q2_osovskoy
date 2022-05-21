package com.example.a2022_q2_osovskoy.data.datasource

import com.example.a2022_q2_osovskoy.data.model.MockedStringDto

interface StringDataSource {

    fun get(): MockedStringDto
}