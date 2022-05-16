package com.example.a2022_q2_osovskoy.presentation.base_phone_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.base_phone_book.FirstEvent
import com.example.a2022_q2_osovskoy.domain.entity.base_phone_book.PersonEvent
import com.example.a2022_q2_osovskoy.domain.entity.base_phone_book.PhoneBookScreenState
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.LoadPersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.RemoveAllPersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.RemovePersonUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.data_base.UploadPersonsFirstTimeUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.LoadFilePersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.RemoveFilePersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.UploadFileFirstTimeUseCase
import com.example.a2022_q2_osovskoy.extentions.SingleLiveEvent
import com.example.a2022_q2_osovskoy.ui.main_screen.MainFragment
import kotlinx.coroutines.launch


class BaseViewModel(
    private val uploadPersonsFirstTimeUseCase: UploadPersonsFirstTimeUseCase,
    private val loadPersonsUseCase: LoadPersonsUseCase,
    private val removeAllPersonsUseCase: RemoveAllPersonsUseCase,
    private val removePersonUseCase: RemovePersonUseCase,
    private val uploadFileFirstTimeUseCase: UploadFileFirstTimeUseCase,
    private val loadFilePersonsUseCase: LoadFilePersonsUseCase,
    private val removeFilePersonsUseCase: RemoveFilePersonsUseCase,
) : ViewModel() {

    private val _phoneBookScreenState = MutableLiveData<PhoneBookScreenState>()
    val phoneBookState = _phoneBookScreenState

    private val _personEvents = MutableLiveData<PersonEvent>()
    val personEvents = _personEvents

    private val _firstEvents = SingleLiveEvent<FirstEvent>()
    val firstEvents: LiveData<FirstEvent> = _firstEvents

    private val _isGranted = MutableLiveData(false)
    val isGranted = _isGranted

    private var counter = CREATE_COUNTER

    fun setGranted(granted: Boolean) {
        viewModelScope.launch {
            _isGranted.value = granted
        }
    }

    fun setPhoneBookState(fragmentFlag: Int) {
        viewModelScope.launch {
            _phoneBookScreenState.value = when (fragmentFlag) {
                MainFragment.FILE_FLAG -> PhoneBookScreenState.File
                MainFragment.DATA_BASE_FLAG -> PhoneBookScreenState.Database
                else -> PhoneBookScreenState.Error
            }
        }
    }

    fun loadPersons() {
        viewModelScope.launch {
            _personEvents.value = PersonEvent.Loading
            _personEvents.value = when (val resultState = loadPersonsUseCase()) {
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
    }

    fun loadFirstTime() {
        viewModelScope.launch {
            if (counter == CREATE_COUNTER) {
                _firstEvents.value = FirstEvent.Loading
                _firstEvents(when (uploadPersonsFirstTimeUseCase()) {
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
    }

    fun deletePerson(person: Person) = viewModelScope.launch {
        removePersonUseCase(person)
        loadPersons()
    }

    fun deleteAllPersons() = viewModelScope.launch {
        removeAllPersonsUseCase
        loadPersons()
    }

    private fun loadDependingOnPhoneBookScreenState(state: PhoneBookScreenState) {
        when (state) {

        }
    }

    companion object {
        const val CREATE_COUNTER = 0
    }
}