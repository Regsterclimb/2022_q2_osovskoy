package com.example.a2022_q2_osovskoy.ui

import android.app.Application
import com.example.a2022_q2_osovskoy.data.data_source.content_provider.PersonsSource
import com.example.a2022_q2_osovskoy.data.data_source.data_base.PhoneDataBase
import com.example.a2022_q2_osovskoy.data.data_source.file.FileLoader
import com.example.a2022_q2_osovskoy.data.repository.BaseRepository
import com.example.a2022_q2_osovskoy.data.repository.data_base.PersonOperationsRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.data_base.PersonsRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.file.FilePersonsRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.use_case.BaseUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.*
import com.example.a2022_q2_osovskoy.domain.use_case.file.LoadFilePersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.RemoveFilePersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.UploadFileFirstTimeUseCase
import kotlin.properties.Delegates

class App : Application() {

    var uploadPersonsFirstTimeUseCase: UploadPersonsFirstTimeUseCase by Delegates.notNull()
    var loadPersonsUseCase: LoadPersonsUseCase by Delegates.notNull()
    var removeAllPersonsUseCase: RemoveAllPersonsUseCase by Delegates.notNull()

    var removeFilePersonsUseCase: RemoveFilePersonsUseCase by Delegates.notNull()
    var loadFilePersonsUseCase: LoadFilePersonsUseCase by Delegates.notNull()
    var uploadFileFirstTimeUseCase: UploadFileFirstTimeUseCase by Delegates.notNull()

    var createPersonUseCase: CreatePersonUseCase by Delegates.notNull()
    var removePersonUseCase: RemovePersonUseCase by Delegates.notNull()
    var updatePersonUseCase: UpdatePersonUseCase by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        val dataBase = PhoneDataBase.create(this).phonesDao()

        uploadPersonsFirstTimeUseCase = UploadPersonsFirstTimeUseCase(BaseUseCase.Base(),
            PersonsRepositoryImpl(BaseRepository.Base(),
                PersonsSource.Base(contentResolver), dataBase))

        loadPersonsUseCase = LoadPersonsUseCase(BaseUseCase.Base(),
            PersonsRepositoryImpl(BaseRepository.Base(),
                PersonsSource.Base(contentResolver), dataBase))

        removeAllPersonsUseCase = RemoveAllPersonsUseCase(BaseUseCase.Base(),
            PersonsRepositoryImpl(BaseRepository.Base(),
                PersonsSource.Base(contentResolver), dataBase))

        val fileRep = FilePersonsRepositoryImpl(
            BaseRepository.Base(),
            FileLoader.Base(this),
            PersonsSource.Base(contentResolver))

        removeFilePersonsUseCase = RemoveFilePersonsUseCase(fileRep, BaseUseCase.Base())

        loadFilePersonsUseCase = LoadFilePersonsUseCase(fileRep, BaseUseCase.Base())

        uploadFileFirstTimeUseCase = UploadFileFirstTimeUseCase(fileRep, BaseUseCase.Base())

        val operationRep = PersonOperationsRepositoryImpl(BaseRepository.Base(), dataBase)

        createPersonUseCase = CreatePersonUseCase(operationRep)

        updatePersonUseCase = UpdatePersonUseCase(operationRep)

        removePersonUseCase = RemovePersonUseCase(operationRep)

    }
}