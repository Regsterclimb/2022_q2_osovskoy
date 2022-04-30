package com.example.a2022_q2_osovskoy.data.repository

import android.util.Log
import com.example.a2022_q2_osovskoy.data.model.toPersonDto
import com.example.a2022_q2_osovskoy.data.model.toPersonEntity
import com.example.a2022_q2_osovskoy.data.storage.data_base.PhonesDao
import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonDataBaseRepository

class PersonRepositoryImpl(
    private val phonesDao: PhonesDao,
) : PersonDataBaseRepository {

    override fun create(person: Person) {
        Log.d("PERSON", "Trying to Create $person")
        phonesDao.insertPerson(person.toPersonDto().toPersonEntity())
    }

    override fun remove(person: Person) {
        Log.d("PERSON", "Trying to Delete $person")
        phonesDao.deletePerson(person = person.toPersonDto().toPersonEntity())
    }

    override fun update(person: Person) {
        Log.d("PERSON", "Trying to Update $person")
        phonesDao.updatePerson(person = person.toPersonDto().toPersonEntity())
    }
}