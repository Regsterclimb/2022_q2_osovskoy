package com.example.a2022_q2_osovskoy.data.datasourse.local.token

import android.content.SharedPreferences
import android.util.Log
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

    //todo() can drop NullPointer
    override fun get(): String {
        val bearer = encryptedSharedPreferences.getString(BEARER_KEY, BEARER_DEFAULT_VALUE)
        Log.d("Bearer", "get $bearer")
        if (bearer != null) return bearer else throw NullPointerException()
    }

    override suspend fun update(token: String) {
        encryptedSharedPreferences.edit {
            putString(BEARER_KEY, token)
        }
    }
}