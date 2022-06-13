package com.example.a2022_q2_osovskoy.di.data

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.data.datasourse.local.AppConfigDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.local.AppConfigDataSourceImpl
import com.example.a2022_q2_osovskoy.data.datasourse.local.TokenDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.local.TokenDataSourceImpl
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.AuthRemoteDataSourceImpl
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.remote.LoansRemoteDataSourceImpl
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import com.example.a2022_q2_osovskoy.di.annotations.BasicSharedPrefs
import com.example.a2022_q2_osovskoy.di.annotations.EncryptedSharedPrefs
import com.example.a2022_q2_osovskoy.extentions.provideMasterKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module(includes = [NetworkModule::class])
interface DataModule {

    companion object {
        private const val SHARED_PREFS_KEY = "my_prefs"
        private const val SECRET_SHARED_PREFS_KEY = "secret_shared_prefs"

        @Provides
        fun provideDispatcherIo(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        fun provideContext(app: App): Context = app.applicationContext

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
            context.getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE)
    }

    @Binds
    @AppScope
    fun bindAppConfigDataSource(impl: AppConfigDataSourceImpl): AppConfigDataSource

    @Binds
    fun bindLoginDataSource(implRemote: AuthRemoteDataSourceImpl): AuthDataSource

    @Binds
    fun bindLoansDataSource(implRemote: LoansRemoteDataSourceImpl): LoansDataSource

    @Binds
    fun bindTokenDataSource(implLocal: TokenDataSourceImpl): TokenDataSource

}