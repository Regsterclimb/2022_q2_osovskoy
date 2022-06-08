package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import javax.inject.Inject

class GetLoginConfigUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(): LoginConfigState = authRepository.getAuthConfig()
}