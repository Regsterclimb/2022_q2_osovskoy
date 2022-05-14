package com.example.a2022_q2_osovskoy.data.repository.file

import com.example.a2022_q2_osovskoy.data.data_source.content_provider.ContentDataBase
import com.example.a2022_q2_osovskoy.data.data_source.file.FileLoader
import com.example.a2022_q2_osovskoy.data.model.PersonDto
import com.example.a2022_q2_osovskoy.data.repository.BaseRepository
import com.example.a2022_q2_osovskoy.domain.repository.PersonsFileRepository

class FilePersonsRepositoryImpl(
    private val baseRepository: BaseRepository,
    private val fileLoader: FileLoader,
    private val contentDataBase: ContentDataBase,
) : PersonsFileRepository {

    override suspend fun uploadFirst() = baseRepository.execute {
        fileLoader.insertAll(contentDataBase.load())
    }

    override suspend fun loadPersonsDto(): List<PersonDto> = baseRepository.execute {
        fileLoader.loadAllPersonDto()
    }

    override suspend fun deleteAllPersonDto() = baseRepository.execute {
        fileLoader.deleteAll()
    }
}