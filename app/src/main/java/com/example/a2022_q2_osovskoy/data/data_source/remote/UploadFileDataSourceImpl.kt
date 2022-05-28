package com.example.a2022_q2_osovskoy.data.data_source.remote

import com.example.a2022_q2_osovskoy.data.data_source.network.ConventApi
import com.example.a2022_q2_osovskoy.data.model.FilePostResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UploadFileDataSourceImpl @Inject constructor(private val conventApi: ConventApi) :
    UploadFileDataSource {

    companion object {
        const val FILE_NAME = "file9232931923"
        const val PART_MEDIA_TYPE = "multipart/form-data"
    }

    override suspend fun upload(file: File): FilePostResponse = conventApi.sendFile(
        MultipartBody.Part.createFormData(
            FILE_NAME,
            file.name,
            file.asRequestBody(PART_MEDIA_TYPE.toMediaTypeOrNull())
        )
    )
}