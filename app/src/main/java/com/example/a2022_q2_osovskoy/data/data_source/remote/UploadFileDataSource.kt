package com.example.a2022_q2_osovskoy.data.data_source.remote

import com.example.a2022_q2_osovskoy.data.model.FilePostResponse
import java.io.File

interface UploadFileDataSource {

    suspend fun upload(file:File) : FilePostResponse
}