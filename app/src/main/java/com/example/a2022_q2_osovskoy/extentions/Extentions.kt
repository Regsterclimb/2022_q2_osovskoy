package com.example.a2022_q2_osovskoy.extentions

import com.example.a2022_q2_osovskoy.data.model.FilePostResponse
import com.example.a2022_q2_osovskoy.domain.entity.FilePostInfo

fun FilePostResponse.toFilePostInfo(): FilePostInfo = FilePostInfo(
    fileExt,
    fileId,
    fileName,
    url)