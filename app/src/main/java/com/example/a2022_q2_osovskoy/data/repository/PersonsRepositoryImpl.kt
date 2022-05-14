package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.data_source.MyData
import com.example.a2022_q2_osovskoy.data.model.PersonDto
import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository

class PersonsRepositoryImpl(private val myData: MyData) : PersonsRepository {

    override fun loadPersons(): List<PersonDto> = myData.loadList()

}