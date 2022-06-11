package com.example.a2022_q2_osovskoy.data.repository

import com.example.a2022_q2_osovskoy.data.datasourse.AnimalDataSource
import com.example.a2022_q2_osovskoy.data.datasourse.PriceDataSource
import com.example.a2022_q2_osovskoy.domain.entities.Animal
import com.example.a2022_q2_osovskoy.domain.entities.MyAnimal
import com.example.a2022_q2_osovskoy.domain.repository.AnimalRepository
import com.maxsch.rxjavalecture.di.annotations.CatsDataSource
import com.maxsch.rxjavalecture.di.annotations.DogsDataSource
import com.maxsch.rxjavalecture.di.annotations.RatsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    @CatsDataSource private val catsDataSource: AnimalDataSource,
    @DogsDataSource private val dogsDataSource: AnimalDataSource,
    @RatsDataSource private val ratsDataSource: AnimalDataSource,
    private val priceDataSource: PriceDataSource,
) : AnimalRepository {

    override suspend fun getAnimals(): List<MyAnimal> = withContext(dispatcher) {
        val animalList: List<Animal> = coroutineScope {
            val cats = async {
                catsDataSource.get()
            }
            val dogs = async {
                dogsDataSource.get()
            }
            val rats = async {
                ratsDataSource.get()
            }
            listOf(cats.await() + dogs.await() + rats.await()).flatten()
        }
        animalList.map { MyAnimal(it.name, it.age, priceDataSource.getPrice(it)) { it.voice() } }
    }
}