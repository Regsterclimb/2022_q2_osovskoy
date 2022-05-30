package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.domain.repository.FileRepository
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

class GetUploadProgress @Inject constructor(private val fileRepository: FileRepository) {

    suspend operator fun invoke(): SharedFlow<Int> = fileRepository.getProgress()
}