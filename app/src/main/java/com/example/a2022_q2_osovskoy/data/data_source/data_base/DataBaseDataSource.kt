package com.example.a2022_q2_osovskoy.data.data_source.data_base

import com.example.a2022_q2_osovskoy.data.data_source.data_base.entity.PersonEntity

interface DataBaseDataSource {

    suspend fun getAllPersons(): List<PersonEntity>

    suspend fun insertAllPersons(list: List<PersonEntity>)

    suspend fun deleteAllPersons()

    suspend fun getPerson(name: String): PersonEntity

    suspend fun deletePerson(person: PersonEntity)

    suspend fun updatePerson(person: PersonEntity)

    suspend fun insertPerson(person: PersonEntity)

    class Base(private val phonesDao: PhonesDao) : DataBaseDataSource {

        override suspend fun getAllPersons(): List<PersonEntity> = phonesDao.getAllPersons()

        override suspend fun getPerson(name: String): PersonEntity = phonesDao.getPerson(name)

        override suspend fun deleteAllPersons() {
            phonesDao.deleteAllPersons()
        }

        override suspend fun insertAllPersons(list: List<PersonEntity>) {
            phonesDao.insertAllPersons(list)
        }

        override suspend fun deletePerson(person: PersonEntity) {
            phonesDao.deletePerson(person)
        }

        override suspend fun updatePerson(person: PersonEntity) {
            phonesDao.updatePerson(person)
        }

        override suspend fun insertPerson(person: PersonEntity) {
            phonesDao.insertPerson(person)
        }
    }
}