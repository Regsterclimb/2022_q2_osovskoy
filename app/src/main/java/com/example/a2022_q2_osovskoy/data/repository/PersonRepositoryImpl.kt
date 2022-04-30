package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.model.toPersonDto
import com.example.a2022_q2_osovskoy.data.model.toPersonEntity
import com.example.a2022_q2_osovskoy.data.storage.data_base.PhonesDao
import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonDataBaseRepository

class PersonRepositoryImpl(
    private val phonesDao: PhonesDao,
) : PersonDataBaseRepository {

    override fun create(person: Person) {
        phonesDao.insertPerson(person.toPersonDto().toPersonEntity())
    }

    override fun remove(person: Person) {
        phonesDao.deletePerson(person = person.toPersonDto().toPersonEntity())
    }

    override fun update(person: Person) {
        phonesDao.updatePerson(person = person.toPersonDto().toPersonEntity())
    }
}