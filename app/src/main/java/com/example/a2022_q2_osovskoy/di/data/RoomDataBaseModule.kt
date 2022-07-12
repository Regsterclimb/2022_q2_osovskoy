package com.example.a2022_q2_osovskoy.di.data

import android.content.Context
import androidx.room.Room
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.LoansDataBase
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity
import com.example.a2022_q2_osovskoy.di.annotations.AppScope
import dagger.Module
import dagger.Provides

@Module
class RoomDataBaseModule {

    @Provides
    @AppScope
    fun provideLoansDao(loansDataBase: LoansDataBase) = loansDataBase.getLoansDao()

    @Provides
    @AppScope
    fun provideRoomDataBase(context: Context): LoansDataBase =
        Room.databaseBuilder(context, LoansDataBase::class.java, LoanEntity.TABLE_NAME)
            .fallbackToDestructiveMigration()
            .build()
}