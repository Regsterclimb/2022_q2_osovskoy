package com.example.a2022_q2_osovskoy.domain.usecase

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.repository.StringRepository
import javax.inject.Inject

class GetStringFromRemoteUseCase @Inject constructor(private val repository: StringRepository) {

    //TODO: DI
    //TODO: сделать так, чтобы repository не пересоздавался для каждого UseCase

    init {
        Log.d("FromRemoteUseCase", hashCode().toString())
    }

    operator fun invoke(): String =
        String.format(repository.getFromRemote() + ", repo hash = " + repository.hashCode())
}