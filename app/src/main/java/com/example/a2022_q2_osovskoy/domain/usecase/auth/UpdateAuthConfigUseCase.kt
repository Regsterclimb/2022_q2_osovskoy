package com.example.a2022_q2_osovskoy.domain.usecase.auth

import com.example.a2022_q2_osovskoy.domain.entity.LoginConfigState
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import javax.inject.Inject

class UpdateAuthConfigUseCase @Inject constructor(private val authRepository: AuthRepository) {

    operator fun invoke(loginConfigState: LoginConfigState) =
        authRepository.updateAuthConfig(loginConfigState)
}