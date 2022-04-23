package com.example.a2022_q2_osovskoy.data.content_provider.storage

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import com.example.a2022_q2_osovskoy.data.content_provider.model.PersonDto

interface DataBase {

    suspend fun loadPersonFromDb(): List<PersonDto>

    class Base(private val contentResolver: ContentResolver) : DataBase {

        override suspend fun loadPersonFromDb(): List<PersonDto> {
            val cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                ),
                null,
                null,
                null
            )
            return useCursor(cursor)
        }

        private fun useCursor(cursor: Cursor?): List<PersonDto> {
            val personsDto = mutableListOf<PersonDto>()

            cursor?.use {
                val name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val number = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

                while (cursor.moveToNext()) {
                    personsDto
                        .add(
                            PersonDto(cursor.getString(name), cursor.getString(number))
                        )
                }
            }
            return personsDto
        }
    }
}