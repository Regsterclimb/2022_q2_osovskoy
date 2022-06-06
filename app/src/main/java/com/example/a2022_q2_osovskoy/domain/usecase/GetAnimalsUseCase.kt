package com.example.a2022_q2_osovskoy.domain.usecase

import com.example.a2022_q2_osovskoy.domain.entities.MyAnimal
import com.example.a2022_q2_osovskoy.domain.repository.AnimalRepository
import javax.inject.Inject

class GetAnimalsUseCase @Inject constructor(private val animalRepository: AnimalRepository) {

    suspend operator fun invoke(): List<MyAnimal> = animalRepository.getAnimals()
}