package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.data.model.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.FileRepository
import javax.inject.Inject

class UploadFileUseCase @Inject constructor(private val fileRepository: FileRepository) {

    suspend operator fun invoke(): ResultState = fileRepository.uploadFile()
}