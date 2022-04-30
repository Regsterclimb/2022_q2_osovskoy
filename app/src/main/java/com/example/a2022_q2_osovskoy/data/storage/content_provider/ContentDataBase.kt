package com.example.a2022_q2_osovskoy.data.storage.content_provider

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import com.example.a2022_q2_osovskoy.data.model.PersonDto

interface ContentDataBase {

    fun loadPersonFromContentProvider(): List<PersonDto>

    class BaseContent(private val contentResolver: ContentResolver) : ContentDataBase {

        override fun loadPersonFromContentProvider(): List<PersonDto> {
            val cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                arrayOf(
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
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
                val name =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val number = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val id = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)

                while (cursor.moveToNext()) {
                    personsDto
                        .add(
                            PersonDto(cursor.getString(id).toLong(),
                                cursor.getString(name),
                                cursor.getString(number))
                        )
                }
            }
            return personsDto
        }
    }
}