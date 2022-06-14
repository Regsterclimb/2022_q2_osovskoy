package com.example.a2022_q2_osovskoy.di.data

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.di.annotations.BasicSharedPrefs
import com.example.a2022_q2_osovskoy.di.annotations.EncryptedSharedPrefs
import com.example.a2022_q2_osovskoy.extentions.provideMasterKey
import dagger.Module
import dagger.Provides

@Module
class SharedPrefModule {

    companion object {
        private const val SHARED_PREFS_KEY = "my_prefs"
        private const val SECRET_SHARED_PREFS_KEY = "secret_shared_prefs"
    }

    @Provides
    @[AppScope EncryptedSharedPrefs]
    fun provideEncryptedSharedPreference(context: Context): SharedPreferences =
        EncryptedSharedPreferences.create(
            context,
            SECRET_SHARED_PREFS_KEY,
            provideMasterKey(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    @Provides
    @[AppScope BasicSharedPrefs]
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)
}