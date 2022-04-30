package com.example.a2022_q2_osovskoy.presentation.file_phone_book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.domain.use_case.FilePersonsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FileBookViewModel(
    private val fileUseCase: FilePersonsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,

    ) : ViewModel() {

    private var _personEvents = MutableLiveData<PersonEvent>()
    val personEvents = _personEvents

    private var counter = 0

    fun onPermissionResult(granted: Boolean) {
        if (granted && counter == 0) {
            first()
            counter++
        } else {
            loadPersons()
        }
    }

    private fun loadPersons() {
        viewModelScope.launch(dispatcher) {
            _personEvents.value = PersonEvent.Loading
            when (val resultState = fileUseCase.loadPersons()) {
                is FilePersonsUseCase.ResultState.Success -> {
                    _personEvents.value =
                        if (resultState.result.isEmpty()) {
                            PersonEvent.Empty
                        } else {
                            PersonEvent.Success(resultState.result)
                        }
                }
                is FilePersonsUseCase.ResultState.Error -> {
                    _personEvents.value = PersonEvent.Error
                }
            }
        }
    }

    private fun first() {
        viewModelScope.launch(dispatcher) {
            _personEvents.value = PersonEvent.Loading
            when (val resultState = fileUseCase.firstLoad()) {
                is FilePersonsUseCase.ResultState.Success -> {
                    _personEvents.value =
                        if (resultState.result.isEmpty()) {
                            PersonEvent.Empty
                        } else {
                            PersonEvent.Success(resultState.result)
                        }
                }
                is FilePersonsUseCase.ResultState.Error -> {
                    _personEvents.value = PersonEvent.Error
                }
            }
        }
    }

    fun deleteAllPersons() {
        viewModelScope.launch(dispatcher) {
            fileUseCase.deleteAllPersons()
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