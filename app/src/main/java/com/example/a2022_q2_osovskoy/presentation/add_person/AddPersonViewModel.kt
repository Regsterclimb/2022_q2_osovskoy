package com.example.a2022_q2_osovskoy.presentation.add_person

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.add_person.FieldsState
import com.example.a2022_q2_osovskoy.domain.entity.add_person.PersonUpdate
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.CreatePersonUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.UpdatePersonUseCase
import kotlinx.coroutines.launch

class AddPersonViewModel(
    private val createPersonUseCase: CreatePersonUseCase,
    private val updatePersonUseCase: UpdatePersonUseCase,
) : ViewModel() {

    private val _isUpdate = MutableLiveData(PersonUpdate(false, Person(DEFAULT_ID)))
    val isUpdate = _isUpdate

    private val _fieldsState = MutableLiveData<FieldsState>(FieldsState.Empty)
    val fieldsState = _fieldsState

    fun updatePerson(person: Person) {
        viewModelScope.launch {
            updatePersonUseCase(person)
        }
    }

    fun createPerson(person: Person) {
        viewModelScope.launch {
            createPersonUseCase(person)
        }
    }

    fun setUpdate(person: Person) {
        viewModelScope.launch {
            _isUpdate.value = PersonUpdate(true, person)
        }
    }

    fun watchFields(name: String, number: String) {
        _fieldsState.value = if (name.isEmpty() && number.isEmpty()) {
            FieldsState.Empty
        } else if (isFieldRightLength(name) || isFieldRightLength(number)) {
            FieldsState.Error
        } else {
            FieldsState.Success(name, number)
        }
    }

    private fun isFieldRightLength(string: String): Boolean = string.length > STRING_LENGTH

    private companion object {
        const val DEFAULT_ID = 0L
        const val STRING_LENGTH = 20
    }
}