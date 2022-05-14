package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.data.model.PersonDto

interface PersonsFileRepository {

    suspend fun uploadFirst()

    suspend fun loadPersonsDto(): List<PersonDto>

    suspend fun deleteAllPersonDto()
}