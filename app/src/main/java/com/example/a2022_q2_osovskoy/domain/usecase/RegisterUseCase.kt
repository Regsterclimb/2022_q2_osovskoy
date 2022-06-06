package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.BaseUser
import com.example.a2022_q2_osovskoy.domain.repository.LoginRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(baseUser: BaseUser) = loginRepository.register(baseUser)
}