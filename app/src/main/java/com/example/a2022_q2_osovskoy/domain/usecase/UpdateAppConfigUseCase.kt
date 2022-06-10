package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entity.AppConfigValue
import com.example.a2022_q2_osovskoy.domain.repository.AppConfigRepository
import javax.inject.Inject

class UpdateAppConfigUseCase @Inject constructor(private val appConfigRepository: AppConfigRepository) {

    operator fun invoke(appConfigValue: AppConfigValue) = appConfigRepository.update(appConfigValue)
}