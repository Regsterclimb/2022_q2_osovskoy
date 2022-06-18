package com.example.a2022_q2_osovskoy.data.datasourse.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.a2022_q2_osovskoy.di.annotations.EncryptedSharedPrefs
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    @EncryptedSharedPrefs private val encryptedSharedPreferences: SharedPreferences,
) : TokenDataSource {

    companion object {
        const val BEARER_KEY = "Bearer_key"
        const val BEARER_DEFAULT_VALUE = ""
    }

    override fun get(): String =
        encryptedSharedPreferences.getString(BEARER_KEY, BEARER_DEFAULT_VALUE)
            ?: BEARER_DEFAULT_VALUE

    override suspend fun update(token: String) {
        encryptedSharedPreferences.edit {
            putString(BEARER_KEY, token)
        }
    }
}