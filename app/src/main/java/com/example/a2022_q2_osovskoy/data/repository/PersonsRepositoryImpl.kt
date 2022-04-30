package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.model.PersonDto
import com.example.a2022_q2_osovskoy.data.model.toPersonEntity
import com.example.a2022_q2_osovskoy.data.storage.content_provider.DataBase
import com.example.a2022_q2_osovskoy.data.storage.data_base.PhonesDao
import com.example.a2022_q2_osovskoy.data.storage.data_base.entity.toPersonDto
import com.example.a2022_q2_osovskoy.domain.repository.PersonRepository

class PersonsRepositoryImpl(
    private val dataBase: DataBase,
    private val phonesDao: PhonesDao,
) : PersonRepository {

    override fun firstLoad() : List<PersonDto> {
        phonesDao.insertAllPersons(dataBase.loadPersonFromContentProvider()
            .map { personDto -> personDto.toPersonEntity() })
        return loadPersonsDto()
    }

    override fun loadPersonsDto(): List<PersonDto> =
        phonesDao.getAllPersons().map { personEntity ->
            personEntity.toPersonDto()
        }

    override fun deleteAllPersonDto() = phonesDao.deleteAllPersons()
}