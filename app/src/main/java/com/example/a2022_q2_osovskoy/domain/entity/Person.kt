package com.example.a2022_q2_osovskoy.domain.entity

import com.example.a2022_q2_osovskoy.data.model.PersonDto


data class Person(
    val id: Long,
    val name: String = "no name",
    val number: String = "no phone number",
) : java.io.Serializable

fun PersonDto.toPerson(): Person = Person(
    id,
    name,
    number
)
