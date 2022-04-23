package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.storage.ItemsData
import com.example.a2022_q2_osovskoy.domain.repository.ItemsRepository

class ItemsRepositoryImpl(private val itemsData: ItemsData) : ItemsRepository {

    override suspend fun loadItems(): List<ItemsData.ListItem> = itemsData.get()
}