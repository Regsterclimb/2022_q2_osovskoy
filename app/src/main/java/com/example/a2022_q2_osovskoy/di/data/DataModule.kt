package com.example.a2022_q2_osovskoy.di.data

import android.content.Context
import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.data.data_source.file.FileDataSource
import com.example.a2022_q2_osovskoy.data.data_source.file.FileDataSourceImpl
import com.example.a2022_q2_osovskoy.data.data_source.remote.UploadFileDataSource
import com.example.a2022_q2_osovskoy.data.data_source.remote.UploadFileDataSourceImpl
import com.example.a2022_q2_osovskoy.data.data_source.remote.support.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor

@Module
interface DataModule {

    companion object {
        @Provides
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        fun provideApplicationContext(app: App): Context = app.applicationContext
    }

    @Binds
    fun bindFileDataSource(impl: FileDataSourceImpl): FileDataSource

    @Binds
    @Reusable
    fun bindProgressReport(impl: ProgressReportImpl): ProgressReport

    @Binds
    fun bindProgressInterceptor(impl: ProgressInterceptor): Interceptor

    @Binds
    fun bindProgressListener(impl: ProgressListenerImpl): ProgressListener

    @Binds
    fun bindUploadFileDataSource(impl: UploadFileDataSourceImpl): UploadFileDataSource
}