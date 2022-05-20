package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.data_source.ItemsDataSource
import com.example.a2022_q2_osovskoy.data.model.ListItemDto
import com.example.a2022_q2_osovskoy.domain.entity.ListItem
import com.example.a2022_q2_osovskoy.domain.repository.ItemsRepository
import com.example.a2022_q2_osovskoy.extentions.toBannerItem
import com.example.a2022_q2_osovskoy.extentions.toStudentItem
import javax.inject.Inject

class ItemsRepositoryImpl @Inject constructor(private val itemsDataSource: ItemsDataSource) :
    ItemsRepository {

    override fun loadItems(): List<ListItem> = itemsDataSource.getItemsDto().map { listItemDto ->
        mapListItemDto(listItemDto)
    }

    private fun mapListItemDto(listItemDto: ListItemDto): ListItem = when (listItemDto) {
        is ListItemDto.StudentItemDto -> {
            listItemDto.toStudentItem()
        }
        is ListItemDto.BannerItemDto -> {
            listItemDto.toBannerItem()
        }
    }
}