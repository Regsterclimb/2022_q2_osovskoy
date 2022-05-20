package com.example.a2022_q2_osovskoy.extentions

import com.example.a2022_q2_osovskoy.data.model.ListItemDto
import com.example.a2022_q2_osovskoy.domain.entity.ListItem

fun ListItemDto.StudentItemDto.toStudentItem(): ListItem =
    ListItem.StudentItem(name, secondName, description, hasPortfolio)

fun ListItemDto.BannerItemDto.toBannerItem(): ListItem = ListItem.BannerItem(title, description)