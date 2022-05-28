package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.domain.entity.ProgressResult
import com.example.a2022_q2_osovskoy.domain.repository.FileRepository
import javax.inject.Inject

class GetUploadProgress @Inject constructor(private val fileRepository: FileRepository) {

    operator fun invoke(): ProgressResult = fileRepository.getProgress()
}