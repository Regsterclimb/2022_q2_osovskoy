package com.example.a2022_q2_osovskoy.data.repository.file

import com.example.a2022_q2_osovskoy.data.data_source.content_provider.PersonsSource
import com.example.a2022_q2_osovskoy.data.data_source.file.FileLoader
import com.example.a2022_q2_osovskoy.data.repository.BaseRepository
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.toPerson
import com.example.a2022_q2_osovskoy.domain.repository.PersonsFileRepository

class FilePersonsRepositoryImpl(
    private val baseRepository: BaseRepository,
    private val fileLoader: FileLoader,
    private val personsSource: PersonsSource,
) : PersonsFileRepository {

    override suspend fun uploadFirst() = baseRepository.execute {
        fileLoader.insertAll(personsSource.load())
    }

    override suspend fun loadPersons(): List<Person> = baseRepository.execute {
        fileLoader.loadAllPersonDto().map { personDto ->
            personDto.toPerson()
        }
    }

    override suspend fun deleteAllPersons() = baseRepository.execute {
        fileLoader.deleteAll()
    }
}