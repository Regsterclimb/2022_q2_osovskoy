package com.example.a2022_q2_osovskoy.presentation.phone_book.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.domain.repository.PersonDataBaseRepository
import com.example.a2022_q2_osovskoy.domain.use_case.PersonUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProviderMainViewModel(
    private val useCase: PersonUseCase,
    private val personDataBaseRepository: PersonDataBaseRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private var _personEvents = MutableLiveData<PersonEvent>()
    val personEvents = _personEvents

    private var counter = 0

    fun onPermissionResult(granted: Boolean) {
        Log.d("ViewModel", granted.toString())
        if (granted && counter == 0) {
            first()
        } else {
            loadPersons()
        }
    }

    private fun loadPersons() {
        Log.d("LIST", "LOADLIST")
        viewModelScope.launch(dispatcher) {
            _personEvents.value = PersonEvent.Loading
            when (val resultState = useCase.loadPersons()) {
                is PersonUseCase.ResultState.Success -> {
                    _personEvents.value =
                        if (resultState.result.isEmpty()) {
                            PersonEvent.Empty
                        } else {
                            PersonEvent.Success(resultState.result)
                        }
                }
                is PersonUseCase.ResultState.Error -> {
                    _personEvents.value = PersonEvent.Error
                }
            }
        }
    }

    private fun first() {
        counter++
        viewModelScope.launch(dispatcher) {
            _personEvents.value = PersonEvent.Loading
            when (val resultState = useCase.firstLoad()) {
                is PersonUseCase.ResultState.Success -> {
                    _personEvents.value =
                        if (resultState.result.isEmpty()) {
                            PersonEvent.Empty
                        } else {
                            PersonEvent.Success(resultState.result)
                        }
                }
                is PersonUseCase.ResultState.Error -> {
                    _personEvents.value = PersonEvent.Error
                }
            }
        }
    }

    fun deletePerson(person: Person) {
        viewModelScope.launch(dispatcher) {
            personDataBaseRepository.remove(person)
            loadPersons()
        }
    }

    fun deleteAllPersons() {
        viewModelScope.launch(dispatcher) {
            useCase.deleteAllPersons()
            loadPersons()
        }
    }

    sealed class PersonEvent {
        class Success(val result: List<Person>) : PersonEvent()
        object Loading : PersonEvent()
        object Error : PersonEvent()
        object Empty : PersonEvent()
    }
}