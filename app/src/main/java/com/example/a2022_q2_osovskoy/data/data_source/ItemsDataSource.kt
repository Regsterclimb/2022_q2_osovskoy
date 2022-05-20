package com.example.a2022_q2_osovskoy.data.data_source

import com.example.a2022_q2_osovskoy.data.model.ListItemDto
import javax.inject.Inject

interface ItemsDataSource {

    fun getItemsDto(): List<ListItemDto>

    class Base @Inject constructor() : ItemsDataSource {

        override fun getItemsDto(): List<ListItemDto> = data

        private val data: List<ListItemDto> = listOf(
            ListItemDto.StudentItemDto(
                name = "Иван",
                secondName = "Иванов",
                description = "Только что выпустился из универа, с Android знаком не сильно",
                hasPortfolio = true,
            ),
            ListItemDto.BannerItemDto(
                title = "Новая заявка",
                description = "Здравствуйте, меня зовут Глеб, ещё не поздно подать заявку?"
            ),
            ListItemDto.StudentItemDto(
                name = "Пётр",
                secondName = "Петров",
                description = "Сеньор-помидор, 30 лет опыта С++, хочу попробовать себя в новом направлении",
                hasPortfolio = false,
            ),
            ListItemDto.StudentItemDto(
                name = "Семён",
                secondName = "Сёменов",
                description = "Прошёл курсы Skillbox, SkillFactory, SkillShare, но не могу найти работу, помогите мне",
                hasPortfolio = false,
            ),
            ListItemDto.StudentItemDto(
                name = "Андрей",
                secondName = "Андреев",
                description = "Мне не придумали длинного описания",
                hasPortfolio = true,
            ),
            ListItemDto.StudentItemDto(
                name = "Егор",
                secondName = "Егоров",
                description = "Lorem ipsum dolor sit amet ya uchenik mne 19 let",
                hasPortfolio = true,
            ),
        )
    }
}