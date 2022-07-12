package com.example.a2022_q2_osovskoy.data.datasourse.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity

@Database(entities = [LoanEntity::class], version = 1)
abstract class LoansDataBase : RoomDatabase() {

    abstract fun getLoansDao(): LoansDao
}