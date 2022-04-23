package com.example.a2022_q2_osovskoy.domain.content_provider.use_case

import android.util.Log
import com.example.a2022_q2_osovskoy.domain.content_provider.model.Person
import com.example.a2022_q2_osovskoy.domain.content_provider.model.toPerson
import com.example.a2022_q2_osovskoy.domain.content_provider.repository.PersonRepository

interface PersonUseCase {


    suspend fun loadPersons(): ResultState

    class Base(private val personRepository: PersonRepository) : PersonUseCase {

        override suspend fun loadPersons(): ResultState =
            try {
                ResultState.Success(result = personRepository.loadPersonsDto().map { personDto ->
                    personDto.toPerson()
                })
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