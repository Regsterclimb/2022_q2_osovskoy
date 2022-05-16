package com.example.a2022_q2_osovskoy.data.data_source.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.INTEGER
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a2022_q2_osovskoy.data.model.PersonDto

@Entity(tableName = PersonEntity.PhoneDb.TABLE_NAME)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PhoneDb.Column.ID, typeAffinity = INTEGER)
    var id: Long,
    @ColumnInfo(name = PhoneDb.Column.NAME)
    var name: String,
    @ColumnInfo(name = PhoneDb.Column.NUMBER)
    var number: String,
) {
    object PhoneDb {
        const val TABLE_NAME = "phones_storage"

        object Column {
            const val ID = "id"
            const val NAME = "name"
            const val NUMBER = "number"
        }
    }
}

fun PersonEntity.toPersonDto(): PersonDto = PersonDto(
    id,
    name,
    number
)