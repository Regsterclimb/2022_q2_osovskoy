package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.data.model.toPerson
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository

interface PersonsLoadUseCase {

    fun load(startWith: String): List<Person>

    class Base(private val repository: PersonsRepository) : PersonsLoadUseCase {

        override fun load(startWith: String): List<Person> = repository.loadPersons()
            .filter { person ->
                person.name.lowercase().startsWith(startWith.lowercase())
            }
            .map { personDto ->
                personDto.toPerson()
            }
    }
}