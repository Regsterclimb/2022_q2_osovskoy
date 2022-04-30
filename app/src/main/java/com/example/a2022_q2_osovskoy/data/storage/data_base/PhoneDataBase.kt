package com.example.a2022_q2_osovskoy.data.storage.data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a2022_q2_osovskoy.data.storage.data_base.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
abstract class PhoneDataBase : RoomDatabase() {

    abstract fun phonesDao(): PhonesDao

    companion object {
        fun create(appContext: Context): PhoneDataBase = Room.databaseBuilder(
            appContext, PhoneDataBase::class.java, PersonEntity.PhoneDb.tableName
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}