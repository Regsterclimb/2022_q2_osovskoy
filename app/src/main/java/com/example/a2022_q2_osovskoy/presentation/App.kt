package com.example.a2022_q2_osovskoy.presentation

import android.app.Application
import com.example.a2022_q2_osovskoy.data.data_source.MyData
import com.example.a2022_q2_osovskoy.data.repository.PersonsRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.use_case.PersonsLoadUseCase

class App : Application() {

    val personUseCase: PersonsLoadUseCase by lazy {
        PersonsLoadUseCase.Base(PersonsRepositoryImpl(MyData.Base()))
    }
}