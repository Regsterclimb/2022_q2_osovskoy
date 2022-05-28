package com.example.a2022_q2_osovskoy.data.data_source.file

import android.content.Context
import java.io.File
import javax.inject.Inject

class FileDataSourceImpl @Inject constructor(private val context: Context) : FileDataSource {

    companion object {
        const val SIMPLE_FILE_NAME = "simple_file_name.txt"
        const val ASSETS_FILE_NAME = "TPS Report.txt"
    }

    override suspend fun get(): File {
        createFileIfNotExist()
        return checkFileIsEmpty(File(String.format(context.filesDir.absolutePath + "/" + SIMPLE_FILE_NAME)))
    }

    private fun readStringFromAssetFile(): String =
        context.assets.open(ASSETS_FILE_NAME).bufferedReader().use {
            it.readText()
        }

    private fun writeStringToLocalFile(string: String) {
        context.openFileOutput(SIMPLE_FILE_NAME, Context.MODE_PRIVATE).use { fileOutputStream ->
            fileOutputStream.write((string).toByteArray())
        }
    }

    private fun createFileIfNotExist() {
        val folder = File(context.filesDir.toString())
        val file = File(String.format(folder.absolutePath + "/" + SIMPLE_FILE_NAME))
        if (!folder.exists()) {
            folder.mkdir()
        }
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    private fun checkFileIsEmpty(file: File): File =
        if (file.length() == 0L) {
            writeStringToLocalFile(readStringFromAssetFile())
            file
        } else {
            file
        }
}