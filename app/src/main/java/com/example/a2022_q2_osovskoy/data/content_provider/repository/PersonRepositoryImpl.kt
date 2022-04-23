package com.example.a2022_q2_osovskoy.data.content_provider.repository

import android.app.Person
import com.example.a2022_q2_osovskoy.data.content_provider.model.PersonDto
import com.example.a2022_q2_osovskoy.data.content_provider.storage.DataBase
import com.example.a2022_q2_osovskoy.domain.content_provider.repository.PersonRepository

class PersonRepositoryImpl(private val dataBase: DataBase) : PersonRepository {

    override suspend fun loadPersonsDto(): List<PersonDto> = dataBase.loadPersonFromDb()

}