package com.example.a2022_q2_osovskoy.domain.content_provider.repository

import android.app.Person
import com.example.a2022_q2_osovskoy.data.content_provider.model.PersonDto

interface PersonRepository {

    suspend fun loadPersonsDto() : List<PersonDto>
}