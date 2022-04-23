package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.data.storage.ItemsData

interface ItemsRepository {

    suspend fun loadItems() : List<ItemsData.ListItem>
}