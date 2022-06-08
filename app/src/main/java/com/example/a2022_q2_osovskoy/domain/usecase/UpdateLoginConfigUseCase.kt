package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import javax.inject.Inject

class UpdateLoginConfigUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(loginConfigState: LoginConfigState) =
        authRepository.updateAuthConfig(loginConfigState)
}