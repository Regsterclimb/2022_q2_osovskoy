package com.example.a2022_q2_osovskoy.data.model

import com.example.a2022_q2_osovskoy.domain.entity.Person

data class PersonDto(val name: String, val number: String)

fun PersonDto.toPerson(): Person = Person(name, number)
