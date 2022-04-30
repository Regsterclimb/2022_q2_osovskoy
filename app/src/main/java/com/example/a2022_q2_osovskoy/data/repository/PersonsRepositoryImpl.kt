package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.model.PersonDto
import com.example.a2022_q2_osovskoy.data.model.toPersonEntity
import com.example.a2022_q2_osovskoy.data.storage.content_provider.ContentDataBase
import com.example.a2022_q2_osovskoy.data.storage.data_base.PhonesDao
import com.example.a2022_q2_osovskoy.data.storage.data_base.entity.toPersonDto
import com.example.a2022_q2_osovskoy.domain.repository.PersonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PersonsRepositoryImpl(
    private val contentDataBase: ContentDataBase,
    private val phonesDao: PhonesDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PersonRepository {

    override suspend fun firstLoad(): List<PersonDto> = withContext(dispatcher) {
        phonesDao.insertAllPersons(contentDataBase.loadPersonFromContentProvider()
            .map { personDto -> personDto.toPersonEntity() })
        loadPersonsDto()
    }

    override fun loadPersonsDto(): List<PersonDto> =
        phonesDao.getAllPersons().map { personEntity ->
            personEntity.toPersonDto()
        }

    override fun deleteAllPersonDto() = phonesDao.deleteAllPersons()
}