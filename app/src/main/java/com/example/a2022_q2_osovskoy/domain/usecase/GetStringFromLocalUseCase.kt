package com.example.a2022_q2_osovskoy.domain.usecase

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.repository.StringRepository
import javax.inject.Inject

class GetStringFromLocalUseCase @Inject constructor(private val repository: StringRepository) {

    init {
        Log.d("FromLocalUseCase", hashCode().toString())
    }

    operator fun invoke(): String =
        String.format(repository.getFromLocal() + ", repo hash = " + repository.hashCode())

}