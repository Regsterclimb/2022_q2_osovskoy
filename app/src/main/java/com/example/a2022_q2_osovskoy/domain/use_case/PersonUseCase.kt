package com.example.a2022_q2_osovskoy.domain.use_case

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.domain.model.toPerson
import com.example.a2022_q2_osovskoy.domain.repository.PersonRepository

interface PersonUseCase {

    fun loadPersons(): ResultState

    fun deleteAllPersons()

    fun firstLoad(): ResultState


    class Base(private val personRepository: PersonRepository) : PersonUseCase {

        override fun loadPersons(): ResultState =
            try {
                ResultState.Success(result = personRepository.loadPersonsDto().map { personDto ->
                    personDto.toPerson()
                })
            } catch (e: RuntimeException) {
                Log.e("ERROR", "ERROR WITH LOADPERSONS")
                ResultState.Error
            }

        override fun deleteAllPersons() {
            personRepository.deleteAllPersonDto()
        }

        override fun firstLoad(): ResultState =
            try {
                ResultState.Success(result = personRepository.firstLoad().map { personDto ->
                    personDto.toPerson()
                }
                )
            } catch (e: RuntimeException) {
                Log.e("ERROR", "ERROR WITH LOADPERSONS")
                ResultState.Error
            }

    }

    sealed class ResultState {
        class Success(val result: List<Person>) : ResultState()
        object Error : ResultState()
    }
}