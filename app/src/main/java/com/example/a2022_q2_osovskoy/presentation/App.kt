package com.example.a2022_q2_osovskoy.presentation

import android.app.Application
import com.example.a2022_q2_osovskoy.data.repository.ItemsRepositoryImpl
import com.example.a2022_q2_osovskoy.data.storage.ItemsData
import com.example.a2022_q2_osovskoy.domain.useCase.ItemsUseCase
import com.example.a2022_q2_osovskoy.presentation.viewmodel.ItemsViewModel

class App : Application() {

    lateinit var itemsUseCase: ItemsUseCase

    override fun onCreate() {
        itemsUseCase = ItemsUseCase.Base(ItemsRepositoryImpl(ItemsData.Base()))
        super.onCreate()
    }
}