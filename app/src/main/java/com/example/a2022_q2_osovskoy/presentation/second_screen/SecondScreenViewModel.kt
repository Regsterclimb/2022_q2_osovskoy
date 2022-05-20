package com.example.a2022_q2_osovskoy.presentation.second_screen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.SecondScreenModelState
import com.example.a2022_q2_osovskoy.domain.entity.SecondScreenViewState
import kotlinx.coroutines.launch
import javax.inject.Inject

class SecondScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<SecondScreenModelState>()
    val state = _state

    init {
        Log.d("ViewModelSecond", "Init + ${hashCode()}")
    }
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
            _state.value = modelState
        }
    }
}