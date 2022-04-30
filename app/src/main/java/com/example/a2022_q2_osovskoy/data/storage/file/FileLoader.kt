package com.example.a2022_q2_osovskoy.data.storage.file

import android.content.Context
import com.example.a2022_q2_osovskoy.data.model.PersonDto
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

interface FileLoader {

    fun insertAll(list: List<PersonDto>)

    fun createFileIfNotExist()

    fun loadAllPersonDto(): List<PersonDto>

    fun deleteAll()

    class Base(private val appContext: Context) : FileLoader {

        companion object {
            const val PHONE_FILE_NAME = "phone_file.json"
        }

        override fun insertAll(list: List<PersonDto>) {
            createFileIfNotExist()
            appContext.openFileOutput(PHONE_FILE_NAME, Context.MODE_PRIVATE)
                .use { fileOutputStream ->
                    fileOutputStream.write(Json.encodeToString(list).toByteArray())
                }
        }

        override fun createFileIfNotExist() {
            val folder = File("${appContext.filesDir}")
            val file = File("${folder.absolutePath}/$PHONE_FILE_NAME")
            if (!folder.exists()) {
                folder.mkdir()
            }
            if (!file.exists()) {
                file.createNewFile()
            }
        }

        override fun loadAllPersonDto(): List<PersonDto> {
            createFileIfNotExist()
            appContext.openFileInput(PHONE_FILE_NAME).use { fileInputStream ->
                val inputText = fileInputStream.bufferedReader().readText()
                if (inputText.isNotEmpty()) {
                    return Json.decodeFromString(inputText)
                } else {
                    throw NullPointerException()
                }
            }
        }

        override fun deleteAll() {
            createFileIfNotExist()
            appContext.openFileOutput(PHONE_FILE_NAME, Context.MODE_PRIVATE)
                .use { fileOutputStream ->
                    fileOutputStream.write("".toByteArray())
                }
        }
    }
}