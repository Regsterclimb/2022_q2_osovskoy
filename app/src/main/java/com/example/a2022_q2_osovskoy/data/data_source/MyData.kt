package com.example.a2022_q2_osovskoy.data.data_source

import com.example.a2022_q2_osovskoy.data.model.PersonDto

interface MyData {

    fun loadList(): List<PersonDto>

    class Base : MyData {

        override fun loadList(): List<PersonDto> = listOf(
            PersonDto("Артем", "+923823938299"),
            PersonDto("Artem", "+923823938299"),
            PersonDto("Victor", "+923823938299"),
            PersonDto("Vasiliy", "+923823938299"),
            PersonDto("Fred", "+923823938299"),
            PersonDto("Felony", "+923823938299"),
            PersonDto("Cringe", "+923823938299"),
            PersonDto("Hogger", "+923823938299"),
            PersonDto("Milissa", "+923823938299"),
            PersonDto("Kartoshka", "+923823938299"),
            PersonDto("Bomx", "+923823938299"),
            PersonDto("krendel", "+923823938299"),
            PersonDto("skillbox", "+923823938299"),
            PersonDto("Ahmed", "+923823938299"),
            PersonDto("Lodha", "+923823938299"))
    }
}