package com.example.a2022_q2_osovskoy.domain.content_provider.model

import com.example.a2022_q2_osovskoy.data.content_provider.model.PersonDto

data class Person(
    val name: String = "no name",
    val number: String = "no phone number",
)

fun PersonDto.toPerson(): Person = Person(
    name,
    number
)
