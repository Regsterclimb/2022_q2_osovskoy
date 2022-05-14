package com.example.a2022_q2_osovskoy.domain.entity.add_person

import com.example.a2022_q2_osovskoy.domain.entity.Person

data class PersonUpdate(
    val isUpdate: Boolean,
    val person: Person,
)
