package com.example.a2022_q2_osovskoy.data.storage

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.a2022_q2_osovskoy.data.remote.dto.CurrencyDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface CurrencyData {

    fun save(list: List<CurrencyDto>)

    fun getCurrencyDto(currencyId: String): CurrencyDto?

    class Base(private val sharedPreferences: SharedPreferences) : CurrencyData {

        companion object {
            const val KEY_CURRENCY = "currencyDto"
        }

        override fun getCurrencyDto(currencyId: String): CurrencyDto? = try {
            restoreData()?.find { currencyDto -> currencyDto.id == currencyId }
        } catch (e: NullPointerException) {
            throw NullPointerException()
        }

        override fun save(list: List<CurrencyDto>) {
            sharedPreferences
                .edit {
                    putString(KEY_CURRENCY, Json.encodeToString(list))
                    apply()
                }
        }

        private fun restoreData(): List<CurrencyDto>? {
            val data = sharedPreferences.getString(KEY_CURRENCY, null)
            return if (data != null) {
                val json = Json { ignoreUnknownKeys = true }
                return json.decodeFromString<List<CurrencyDto>>(
                    data
                )
            } else {
                null
            }
        }
    }
}