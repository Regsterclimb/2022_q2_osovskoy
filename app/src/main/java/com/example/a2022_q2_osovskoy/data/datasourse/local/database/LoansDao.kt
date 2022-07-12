package com.example.a2022_q2_osovskoy.data.datasourse.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a2022_q2_osovskoy.data.datasourse.local.database.model.LoanEntity

@Dao
interface LoansDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(loansList: List<LoanEntity>)

    @Query("SELECT * FROM ${LoanEntity.TABLE_NAME}")
    suspend fun getAll(): List<LoanEntity>

    @Query("DELETE FROM ${LoanEntity.TABLE_NAME}")
    suspend fun deleteAll()

    @Query("SELECT * FROM ${LoanEntity.TABLE_NAME} WHERE ${LoanEntity.ID} = :loanId")
    suspend fun getById(loanId: Long): LoanEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoan(loanEntity: LoanEntity)

}