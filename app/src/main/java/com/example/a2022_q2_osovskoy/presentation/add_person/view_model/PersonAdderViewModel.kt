package com.example.a2022_q2_osovskoy.presentation.add_person.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonDataBaseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonAdderViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    private val personDataBaseRepository: PersonDataBaseRepository,
) : ViewModel() {

    fun updatePerson(person:Person) {
        viewModelScope.launch(dispatcher) {
            personDataBaseRepository.update(person)
        }
    }
    fun createPerson(person: Person){
        viewModelScope.launch(dispatcher) {
            personDataBaseRepository.create(person)
        }
    }
}