package com.example.a2022_q2_osovskoy.data.data_source.content_provider

import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import com.example.a2022_q2_osovskoy.data.model.PersonDto

interface PersonsSource {

    fun load(): List<PersonDto>

    class Base(private val contentResolver: ContentResolver) : PersonsSource {

        override fun load(): List<PersonDto> = useCursor(
            contentResolver.query(
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
        )

        private fun useCursor(cursor: Cursor?): List<PersonDto> {
            val personsDto = mutableListOf<PersonDto>()

            cursor?.use {
                val name = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val number = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                val id = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)

                while (cursor.moveToNext()) {
                    personsDto
                        .add(
                            PersonDto(cursor.getString(id).toLong(),
                                cursor.getString(name),
                                cursor.getString(number)
                            )
                        )
                }
            }
            return personsDto
        }
    }
}