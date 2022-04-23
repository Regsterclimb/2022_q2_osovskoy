package com.example.a2022_q2_osovskoy.presentation.content_provider.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.content_provider.model.Person
import com.example.a2022_q2_osovskoy.domain.content_provider.use_case.PersonUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProviderMainViewModel(
    private val useCase: PersonUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private var _personEvents = MutableLiveData<PersonEvent>()
    val personEvents = _personEvents

    fun loadPersons() {
        viewModelScope.launch(dispatcher) {
            _personEvents.value = PersonEvent.Loading
            delay(50)
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

    sealed class PersonEvent {
        class Success(val result: List<Person>) : PersonEvent()
        object Loading : PersonEvent()
        object Error : PersonEvent()
        object Empty : PersonEvent()
    }
}