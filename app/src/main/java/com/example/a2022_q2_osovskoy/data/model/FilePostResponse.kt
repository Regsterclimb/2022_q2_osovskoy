package com.example.a2022_q2_osovskoy.data.model


import com.google.gson.annotations.SerializedName


data class FilePostResponse(
    @SerializedName("FileExt")
    val fileExt: String,
    @SerializedName("FileId")
    val fileId: String,
    @SerializedName("FileName")
    val fileName: String,
    @SerializedName("Url")
    val url: String,
)