package com.example.a2022_q2_osovskoy.presentation

import android.app.Application
import com.example.a2022_q2_osovskoy.data.content_provider.repository.PersonRepositoryImpl
import com.example.a2022_q2_osovskoy.data.content_provider.storage.DataBase
import com.example.a2022_q2_osovskoy.domain.content_provider.use_case.PersonUseCase

class App : Application() {

    lateinit var useCase: PersonUseCase

    override fun onCreate() {
        super.onCreate()
        useCase = PersonUseCase.Base(
            PersonRepositoryImpl(
                DataBase.Base(
                    applicationContext.contentResolver
                )
            )
        )
    }
}