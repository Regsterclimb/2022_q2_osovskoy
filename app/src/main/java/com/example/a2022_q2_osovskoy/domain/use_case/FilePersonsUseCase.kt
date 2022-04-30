package com.example.a2022_q2_osovskoy.domain.use_case

import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.domain.model.toPerson
import com.example.a2022_q2_osovskoy.domain.repository.PersonRepository

interface FilePersonsUseCase {

    fun loadPersons(): ResultState

    fun deleteAllPersons()

    suspend fun firstLoad(): ResultState

    class Base(private val personRepository: PersonRepository) : FilePersonsUseCase {

        override fun loadPersons(): ResultState = try {
            ResultState.Success(result = personRepository.loadPersonsDto().map { personDto ->
                personDto.toPerson()
            }
            )
        } catch (e: RuntimeException) {
            ResultState.Error
        }

        override fun deleteAllPersons() = personRepository.deleteAllPersonDto()

        override suspend fun firstLoad(): ResultState = try {
            ResultState.Success(result = personRepository.firstLoad().map { personDto ->
                personDto.toPerson()
            }
            )
        } catch (e: RuntimeException) {
            ResultState.Error
        }
    }

    sealed class ResultState {
        class Success(val result: List<Person>) : ResultState()
        object Error : ResultState()
    }
}