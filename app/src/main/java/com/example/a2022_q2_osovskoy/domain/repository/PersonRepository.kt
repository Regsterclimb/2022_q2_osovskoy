package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.data.model.PersonDto

interface PersonRepository {

    suspend fun firstLoad() : List<PersonDto>

    fun loadPersonsDto(): List<PersonDto>

    fun deleteAllPersonDto()
}