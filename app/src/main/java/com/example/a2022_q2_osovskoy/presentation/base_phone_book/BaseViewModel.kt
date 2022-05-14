package com.example.a2022_q2_osovskoy.presentation.base_phone_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.base_phone_book.FirstEvent
import com.example.a2022_q2_osovskoy.domain.entity.base_phone_book.PersonEvent
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.PersonRemoveUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.PersonsFirstUploadUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.PersonsLoaderUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.PersonsRemoverUseCase
import com.example.a2022_q2_osovskoy.extentions.SingleLiveEvent
import kotlinx.coroutines.launch


class BaseViewModel(
    private val personsFirstUploadUseCase: PersonsFirstUploadUseCase,
    private val personsLoaderUseCase: PersonsLoaderUseCase,
    private val personsRemoverUseCase: PersonsRemoverUseCase,
    private val personRemoveUseCase: PersonRemoveUseCase,
) : ViewModel() {

    private val _personEvents = MutableLiveData<PersonEvent>()
    val personEvents: LiveData<PersonEvent> = _personEvents

    private val _firstEvents = SingleLiveEvent<FirstEvent>()
    val firstEvents: LiveData<FirstEvent> = _firstEvents

    private val _isGranted = MutableLiveData(false)
    val isGranted = _isGranted

    private var counter = CREATE_COUNTER

    fun setGranted(granted: Boolean) {
        _isGranted.value = granted
    }

    fun loadPersons() = viewModelScope.launch {
        _personEvents.value = (PersonEvent.Loading)
        val resultState = personsLoaderUseCase.load()
        _personEvents.value = when (resultState) {
            is ResultState.Success -> {
                if (resultState.result.isNotEmpty()) {
                    PersonEvent.Success(resultState.result)
                } else {
                    PersonEvent.Empty
                }
            }
            is ResultState.Error -> PersonEvent.Error
        }
    }

    fun loadFirstTime() = viewModelScope.launch {
        if (counter == CREATE_COUNTER) {
            _firstEvents.value = FirstEvent.Loading
            val resultState = personsFirstUploadUseCase.upload()
            _firstEvents(when (resultState) {
                is ResultState.Success -> {
                    counter++
                    loadPersons()
                    FirstEvent.Success
                }
                is ResultState.Error -> FirstEvent.Error
            }
            )
        } else {
            loadPersons()
        }
    }

    fun deletePerson(person: Person) = viewModelScope.launch {
        personRemoveUseCase.remove(person)
        loadPersons()
    }

    fun deleteAllPersons() = viewModelScope.launch {
        personsRemoverUseCase.deleteAll()
        loadPersons()
    }

    companion object {
        const val CREATE_COUNTER = 0
    }
}