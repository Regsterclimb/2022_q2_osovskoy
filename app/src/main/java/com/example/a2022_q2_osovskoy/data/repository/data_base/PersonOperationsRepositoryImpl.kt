package com.example.a2022_q2_osovskoy.data.repository.data_base

import com.example.a2022_q2_osovskoy.data.data_source.data_base.PhonesDao
import com.example.a2022_q2_osovskoy.data.model.toPersonDto
import com.example.a2022_q2_osovskoy.data.model.toPersonEntity
import com.example.a2022_q2_osovskoy.data.repository.BaseRepository
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository


class PersonOperationsRepositoryImpl(
    private val baseRepository: BaseRepository,
    private val phonesDao: PhonesDao,
) : PersonOperationsRepository {
    
    override suspend fun create(person: Person) = baseRepository.execute {
        phonesDao.insertPerson(person = person.toPersonDto().toPersonEntity())
    }

    override suspend fun remove(person: Person) = baseRepository.execute {
        phonesDao.deletePerson(person = person.toPersonDto().toPersonEntity())
    }

    override suspend fun update(person: Person) = baseRepository.execute {
        phonesDao.updatePerson(person = person.toPersonDto().toPersonEntity())
    }
}
