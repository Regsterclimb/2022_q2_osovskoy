package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.repository.StringRepository
import javax.inject.Inject

class GetStringFromRemoteUseCase @Inject constructor(private val repository: StringRepository) {

    operator fun invoke(): String =
        String.format(repository.getFromRemote().string + ", repo hash = " + repository.hashCode())
}