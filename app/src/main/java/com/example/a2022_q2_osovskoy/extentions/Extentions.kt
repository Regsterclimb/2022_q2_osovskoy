package com.example.a2022_q2_osovskoy.extentions

import com.example.a2022_q2_osovskoy.data.model.MockedStringDto
import com.example.a2022_q2_osovskoy.domain.entity.MockedString

fun MockedStringDto.toMockedString(): MockedString = MockedString(string)