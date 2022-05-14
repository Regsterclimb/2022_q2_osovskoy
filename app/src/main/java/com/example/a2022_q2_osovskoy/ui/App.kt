package com.example.a2022_q2_osovskoy.ui

import android.app.Application
import com.example.a2022_q2_osovskoy.data.data_source.content_provider.ContentDataBase
import com.example.a2022_q2_osovskoy.data.data_source.data_base.PhoneDataBase
import com.example.a2022_q2_osovskoy.data.data_source.file.FileLoader
import com.example.a2022_q2_osovskoy.data.repository.BaseRepository
import com.example.a2022_q2_osovskoy.data.repository.data_base.PersonOperationsRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.data_base.PersonsRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.file.FilePersonsRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.repository.PersonOperationsRepository
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.*
import com.example.a2022_q2_osovskoy.domain.use_case.file.FileFirstUploadUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.FilePersonsLoaderUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.FilePersonsRemoverUseCase
import kotlin.properties.Delegates

class App : Application() {

    var personsFirstUploadUseCase: PersonsFirstUploadUseCase by Delegates.notNull()
    var personsLoaderUseCase: PersonsLoaderUseCase by Delegates.notNull()
    var personsRemoverUseCase: PersonsRemoverUseCase by Delegates.notNull()

    var filePersonsRemoverUseCase: FilePersonsRemoverUseCase by Delegates.notNull()
    var filePersonsLoaderUseCase: FilePersonsLoaderUseCase by Delegates.notNull()
    var fileFirstUploadUseCase: FileFirstUploadUseCase by Delegates.notNull()

    var personOperationsRepository: PersonOperationsRepository by Delegates.notNull()

    var personCreateUseCase: PersonCreateUseCase by Delegates.notNull()
    var personRemoveUseCase: PersonRemoveUseCase by Delegates.notNull()
    var personUpdateUseCase: PersonUpdateUseCase by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        val dataBase = PhoneDataBase.create(this).phonesDao()

        personsFirstUploadUseCase = PersonsFirstUploadUseCase.Base(BaseUseCase.Base(),
            PersonsRepositoryImpl(BaseRepository.Base(),
                ContentDataBase.Base(contentResolver), dataBase))

        personsLoaderUseCase = PersonsLoaderUseCase.Base(BaseUseCase.Base(),
            PersonsRepositoryImpl(BaseRepository.Base(),
                ContentDataBase.Base(contentResolver), dataBase))

        personsRemoverUseCase = PersonsRemoverUseCase.Base(BaseUseCase.Base(),
            PersonsRepositoryImpl(BaseRepository.Base(),
                ContentDataBase.Base(contentResolver), dataBase))

        val fileRep = FilePersonsRepositoryImpl(
            BaseRepository.Base(),
            FileLoader.Base(this),
            ContentDataBase.Base(contentResolver))

        filePersonsRemoverUseCase = FilePersonsRemoverUseCase.Base(fileRep, BaseUseCase.Base())

        filePersonsLoaderUseCase = FilePersonsLoaderUseCase.Base(fileRep, BaseUseCase.Base())

        fileFirstUploadUseCase = FileFirstUploadUseCase.Base(fileRep, BaseUseCase.Base())

        personOperationsRepository = PersonOperationsRepositoryImpl(BaseRepository.Base(), dataBase)

        val operationRep = PersonOperationsRepositoryImpl(BaseRepository.Base(), dataBase)

        personCreateUseCase = PersonCreateUseCase.Base(operationRep)

        personUpdateUseCase = PersonUpdateUseCase.Base(operationRep)

        personRemoveUseCase = PersonRemoveUseCase.Base(operationRep)

    }
}