package com.example.a2022_q2_osovskoy.domain.usecase.auth

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authRepository: AuthRepository) {

    suspend operator fun invoke(baseUser: BaseUser) = authRepository.login(baseUser)
}