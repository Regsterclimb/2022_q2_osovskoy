package com.example.a2022_q2_osovskoy.presentation.add_person

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.add_person.FieldsState
import com.example.a2022_q2_osovskoy.domain.entity.add_person.PersonUpdate
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.PersonCreateUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.PersonUpdateUseCase
import kotlinx.coroutines.launch

class AddPersonViewModel(
    private val personCreateUseCase: PersonCreateUseCase,
    private val personUpdateUseCase: PersonUpdateUseCase,
) : ViewModel() {

    private val _isUpdate = MutableLiveData(PersonUpdate(false, Person(DEFAULT_ID)))
    val isUpdate = _isUpdate

    private val _fieldsState = MutableLiveData<FieldsState>(FieldsState.Empty)
    val fieldsState = _fieldsState

    fun updatePerson(person: Person) = viewModelScope.launch {
        personUpdateUseCase.update(person)
    }

    fun createPerson(person: Person) = viewModelScope.launch {
        personCreateUseCase.create(person)
    }

    fun setUpdate(person: Person) {
        _isUpdate.value = PersonUpdate(true, person)
    }

    fun watchFields(name: String, number: String) {
        _fieldsState.value = if (name.isEmpty() && number.isEmpty()) {
            FieldsState.Empty
        } else if (fieldsLength(name) || fieldsLength(number)) {
            FieldsState.Error
        } else {
            FieldsState.Success(name, number)
        }
    }

    private fun fieldsLength(string: String): Boolean = string.length > 20

    private companion object {
        const val DEFAULT_ID = 0L
    }
}