package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.repository.ItemsRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(private val itemsRepository: ItemsRepository) {

    operator fun invoke(): ResultState = try {
        ResultState.Success(result = itemsRepository.loadItems())
    } catch (e: IllegalArgumentException) {
        ResultState.Error
    }
}