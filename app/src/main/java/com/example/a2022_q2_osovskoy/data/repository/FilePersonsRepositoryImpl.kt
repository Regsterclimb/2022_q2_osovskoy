package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.model.PersonDto
import com.example.a2022_q2_osovskoy.data.storage.content_provider.ContentDataBase
import com.example.a2022_q2_osovskoy.data.storage.file.FileLoader
import com.example.a2022_q2_osovskoy.domain.repository.PersonRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FilePersonsRepositoryImpl(
    private val fileLoader: FileLoader,
    private val contentDataBase: ContentDataBase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PersonRepository {

    override suspend fun firstLoad(): List<PersonDto> = withContext(dispatcher) {
        fileLoader.insertAll(contentDataBase.loadPersonFromContentProvider())
        loadPersonsDto()
    }

    override fun loadPersonsDto(): List<PersonDto> = fileLoader.loadAllPersonDto()

    override fun deleteAllPersonDto() = fileLoader.deleteAll()
}