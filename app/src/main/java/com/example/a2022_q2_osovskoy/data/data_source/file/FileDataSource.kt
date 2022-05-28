package com.example.a2022_q2_osovskoy.data.data_source.file

import java.io.File

interface FileDataSource {
    suspend fun  get() : File
}