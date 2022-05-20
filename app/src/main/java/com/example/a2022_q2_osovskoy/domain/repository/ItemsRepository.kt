package com.example.a2022_q2_osovskoy.domain.repository

import com.example.a2022_q2_osovskoy.domain.entity.ListItem

interface ItemsRepository {

    fun loadItems(): List<ListItem>
}