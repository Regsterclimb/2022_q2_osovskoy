package com.example.a2022_q2_osovskoy.data.datasourse.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LoanEntity.TABLE_NAME)
data class LoanEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = ID)
    val id: Long,
    val date: String,
    val firstName: String,
    val lastName: String,
    val amount: Double,
    val period: Int,
    val phoneNumber: String,
    val state: String,
    val percent: Double,
) {
    companion object {
        const val TABLE_NAME = "loans_storage"
        const val ID = "id"
    }
}