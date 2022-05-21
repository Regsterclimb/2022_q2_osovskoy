package com.example.a2022_q2_osovskoy.presentation.second_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.ui.second_screen.SecondScreenViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondScreenViewModel @Inject constructor() : ViewModel() {

    private val _secondScreenState = MutableLiveData<SecondScreenModelState>()
    val state = _secondScreenState

    fun renderViewState(viewState: SecondScreenViewState) {
        when (viewState) {
            is SecondScreenViewState.ClickOnAcceptButton -> {
                if (viewState.textInput.isNotEmpty()) {
                    setModelState(SecondScreenModelState.Success)
                } else {
                    setModelState(SecondScreenModelState.WrongTextInput)
                }
            }
            is SecondScreenViewState.ExitFromScreen -> {
                setModelState(SecondScreenModelState.ExitFromScreen)
            }
        }
    }

    private fun setModelState(modelState: SecondScreenModelState) {
        viewModelScope.launch {
            _secondScreenState.value = modelState
        }
    }
}