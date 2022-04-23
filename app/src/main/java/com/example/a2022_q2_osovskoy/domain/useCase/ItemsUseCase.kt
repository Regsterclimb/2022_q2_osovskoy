package com.example.a2022_q2_osovskoy.domain.useCase

import android.util.Log
import com.example.a2022_q2_osovskoy.data.storage.ItemsData
import com.example.a2022_q2_osovskoy.domain.repository.ItemsRepository

interface ItemsUseCase {

    suspend fun loadItems(): ResultState

    class Base(private val itemsRepository: ItemsRepository) : ItemsUseCase {

        override suspend fun loadItems() : ResultState =
            try {
                ResultState.Success(result = itemsRepository.loadItems())
            } catch (e: RuntimeException) {
                Log.e("ERROR", "Error with loadItems")
                ResultState.Error
            }
    }

    sealed class ResultState {
        class Success(val result: List<ItemsData.ListItem>) : ResultState()
        object Error : ResultState()
    }
}