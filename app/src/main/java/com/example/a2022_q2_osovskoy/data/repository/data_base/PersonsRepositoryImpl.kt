package com.example.a2022_q2_osovskoy.data.repository.data_base

import com.example.a2022_q2_osovskoy.data.data_source.content_provider.PersonsSource
import com.example.a2022_q2_osovskoy.data.data_source.data_base.PhonesDao
import com.example.a2022_q2_osovskoy.data.data_source.data_base.entity.toPersonDto
import com.example.a2022_q2_osovskoy.data.model.toPersonEntity
import com.example.a2022_q2_osovskoy.data.repository.BaseRepository
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.toPerson
import com.example.a2022_q2_osovskoy.domain.repository.PersonsRepository


class PersonsRepositoryImpl(
    private val baseRepository: BaseRepository,
    private val personsSource: PersonsSource,
    private val phonesDao: PhonesDao,
) : PersonsRepository {

    override suspend fun uploadFirst() = baseRepository.execute {
        phonesDao.insertAllPersons(
            personsSource.load()
                .map { personDto ->
                    personDto.toPersonEntity()
                }
        )
    }

    override suspend fun loadPersons(): List<Person> = baseRepository.execute {
        phonesDao.getAllPersons().map { personEntity ->
            personEntity.toPersonDto().toPerson()
        }
    }

    override suspend fun deleteAllPersonDto() = baseRepository.execute {
        phonesDao.deleteAllPersons()
    }
}
