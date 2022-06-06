package com.example.a2022_q2_osovskoy.data.datasourse

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.entities.Animal
import com.example.a2022_q2_osovskoy.domain.entities.Cat
import com.example.a2022_q2_osovskoy.domain.entities.Dog
import com.example.a2022_q2_osovskoy.domain.entities.Rat
import kotlinx.coroutines.delay
import javax.inject.Inject

class PriceDataSourceImpl @Inject constructor() :
    PriceDataSource {

    companion object {
        const val CAT_PRICE = 1
        const val DOG_PRICE = 2
        const val RAT_PRICE = 3
    }

    override suspend fun getPrice(animal: Animal): Int {
        Log.d("AnimalRepositoryImpl", "GetPriceStarted")
        delay(300)
        Log.d("AnimalRepositoryImpl", "GetPriceDone")
        return when (animal) {
            is Cat -> CAT_PRICE
            is Dog -> DOG_PRICE
            is Rat -> RAT_PRICE
            else -> throw IllegalArgumentException("Unknown animal")
        }
    }
}