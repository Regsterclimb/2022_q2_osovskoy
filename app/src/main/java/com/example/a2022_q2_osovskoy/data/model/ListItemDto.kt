package com.example.a2022_q2_osovskoy.data.model

sealed class ListItemDto {

    class StudentItemDto(
        val name: String,
        val secondName: String,
        val description: String,
        val hasPortfolio: Boolean,
    ) : ListItemDto()

    class BannerItemDto(val title: String, val description: String) : ListItemDto()
}