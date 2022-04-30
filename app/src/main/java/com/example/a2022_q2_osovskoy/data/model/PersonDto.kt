package com.example.a2022_q2_osovskoy.data.model

import com.example.a2022_q2_osovskoy.data.storage.data_base.entity.PersonEntity
import com.example.a2022_q2_osovskoy.domain.model.Person

data class PersonDto(
    val id: Long,
    val name: String = "no name",
    val number: String = "no phone number",
)

fun PersonDto.toPersonEntity(): PersonEntity = PersonEntity(
    id,
    name,
    number
)

fun Person.toPersonDto(): PersonDto = PersonDto(
    id,
    name,
    number
)
