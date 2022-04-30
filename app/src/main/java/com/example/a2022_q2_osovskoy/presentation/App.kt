package com.example.a2022_q2_osovskoy.presentation

import android.app.Application
import com.example.a2022_q2_osovskoy.data.repository.PersonRepositoryImpl
import com.example.a2022_q2_osovskoy.data.repository.PersonsRepositoryImpl
import com.example.a2022_q2_osovskoy.data.storage.content_provider.DataBase
import com.example.a2022_q2_osovskoy.data.storage.data_base.PhoneDataBase
import com.example.a2022_q2_osovskoy.domain.use_case.PersonUseCase
import kotlin.properties.Delegates

class App : Application() {

    var personUseCase: PersonUseCase by Delegates.notNull()
    var personRepositoryImpl:PersonRepositoryImpl by Delegates.notNull()

    override fun onCreate() {
        super.onCreate()
        val dataBase = PhoneDataBase.create(this).phonesDao()

        personRepositoryImpl = PersonRepositoryImpl((dataBase))
        personUseCase = PersonUseCase.Base(
            PersonsRepositoryImpl(
                DataBase.Base(
                    applicationContext.contentResolver
                ), dataBase)
        )
    }
}