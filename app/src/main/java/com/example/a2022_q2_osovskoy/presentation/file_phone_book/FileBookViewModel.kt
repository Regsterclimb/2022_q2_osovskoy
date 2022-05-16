package com.example.a2022_q2_osovskoy.presentation.file_phone_book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.file_phone_book.FileFirstEvent
import com.example.a2022_q2_osovskoy.domain.entity.file_phone_book.FilePersonEvent
import com.example.a2022_q2_osovskoy.domain.use_case.file.LoadFilePersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.RemoveFilePersonsUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.file.UploadFileFirstTimeUseCase
import com.example.a2022_q2_osovskoy.extentions.SingleLiveEvent
import kotlinx.coroutines.launch

class FileBookViewModel(
    private val loadFilePersonsUseCase: LoadFilePersonsUseCase,
    private val uploadFileFirstTimeUseCase: UploadFileFirstTimeUseCase,
    private val removeFilePersonsUseCase: RemoveFilePersonsUseCase,
) : ViewModel() {

    private val _personEvents = MutableLiveData<FilePersonEvent>()
    val personEvents: LiveData<FilePersonEvent> = _personEvents

    private val _firstEvents = SingleLiveEvent<FileFirstEvent>()
    val firstEvents: LiveData<FileFirstEvent> = _firstEvents

    private val _isGranted = MutableLiveData(false)
    val isGranted = _isGranted

    private var counter = CREATE_COUNTER

    fun setGranted(granted: Boolean) {
        _isGranted.value = granted
    }

    fun loadPersons() = viewModelScope.launch {
        _personEvents.value = FilePersonEvent.Loading
        val resultState = loadFilePersonsUseCase()
        _personEvents.value = when (resultState) {
            is ResultState.Success -> {
                if (resultState.result.isNotEmpty()) {
                    FilePersonEvent.Success(resultState.result)
                } else {
                    FilePersonEvent.Empty
                }
            }
            is ResultState.Error -> FilePersonEvent.Error
        }
    }
    //todo()
    fun loadFirstTime() = viewModelScope.launch {
        if (counter == CREATE_COUNTER) {
            _firstEvents.value = FileFirstEvent.Loading
            val resultState = uploadFileFirstTimeUseCase()
            _firstEvents(when (resultState) {
                is ResultState.Success -> {
                    loadPersons()
                    counter++
                    FileFirstEvent.Success
                }
                is ResultState.Error -> FileFirstEvent.Error
            }
            )
        } else {
            loadPersons()
        }
    }

    fun deleteAllPersons() = viewModelScope.launch {
        removeFilePersonsUseCase()
        loadPersons()
    }

    companion object {
        const val CREATE_COUNTER = 0
    }
}