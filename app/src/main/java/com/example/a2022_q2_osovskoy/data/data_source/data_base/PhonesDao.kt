package com.example.a2022_q2_osovskoy.data.data_source.data_base

import androidx.room.*
import com.example.a2022_q2_osovskoy.data.data_source.data_base.entity.PersonEntity

@Dao
interface PhonesDao {

    private companion object {
        const val TABLE_NAME = PersonEntity.PhoneDb.tableName
        const val NAME = PersonEntity.PhoneDb.Column.name
    }

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllPersons(): List<PersonEntity>

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteAllPersons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPersons(list: List<PersonEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE $NAME = :name")
    fun getPerson(name: String): PersonEntity

    @Delete
    fun deletePerson(person: PersonEntity)

    @Update
    fun updatePerson(person: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: PersonEntity)
}