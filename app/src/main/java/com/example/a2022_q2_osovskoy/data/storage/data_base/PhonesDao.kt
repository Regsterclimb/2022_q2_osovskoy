package com.example.a2022_q2_osovskoy.data.storage.data_base

import androidx.room.*
import com.example.a2022_q2_osovskoy.data.storage.data_base.entity.PersonEntity

@Dao
interface PhonesDao {

    private companion object {
        const val TABLE_NAME = PersonEntity.PhoneDb.tableName
        const val NAME = PersonEntity.PhoneDb.Column.name
        const val ID_COLUMN = PersonEntity.PhoneDb.Column.id
    }

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllPersons(): List<PersonEntity>

    @Query("DELETE FROM $TABLE_NAME")
    fun deleteAllPersons()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPersons(list: List<PersonEntity>)

    @Query("SELECT * FROM $TABLE_NAME WHERE $NAME = :name")
    fun getPerson(name: String): PersonEntity

    @Delete()
    fun deletePerson(person: PersonEntity)

    @Update
    fun updatePerson(person: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(person: PersonEntity)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $ID_COLUMN DESC LIMIT 1")
    fun getLastItemId() : PersonEntity?
}