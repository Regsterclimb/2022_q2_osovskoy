package com.example.a2022_q2_osovskoy.domain.entity

sealed class ListItem {

    class StudentItem(
        val name: String,
        val secondName: String,
        val description: String,
        val hasPortfolio: Boolean,
    ) : ListItem()

    class BannerItem(
        val title: String,
        val description: String,
    ) : ListItem()
}