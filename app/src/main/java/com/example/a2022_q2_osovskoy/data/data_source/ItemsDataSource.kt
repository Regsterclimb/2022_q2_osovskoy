package com.example.a2022_q2_osovskoy.data.data_source

import com.example.a2022_q2_osovskoy.data.model.ListItemDto

interface ItemsDataSource {

    fun getItemsDto(): List<ListItemDto>
}