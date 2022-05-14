package com.example.a2022_q2_osovskoy.data.data_source.data_base.entity

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.INTEGER
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a2022_q2_osovskoy.data.model.PersonDto

@Entity(tableName = PersonEntity.PhoneDb.tableName)
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PhoneDb.Column.id, typeAffinity = INTEGER)
    var id: Long,
    @ColumnInfo(name = PhoneDb.Column.name)
    var name: String,
    @ColumnInfo(name = PhoneDb.Column.number)
    var number: String,
) {
    object PhoneDb {
        const val tableName = "phones_storage"

        object Column {
            const val id = "id"
            const val name = "name"
            const val number = "number"
        }
    }
}

fun PersonEntity.toPersonDto(): PersonDto = PersonDto(
    id,
    name,
    number
)