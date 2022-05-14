package com.example.a2022_q2_osovskoy.presentation

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.ViewColorState
import com.example.a2022_q2_osovskoy.domain.entity.ViewEvent
import com.example.a2022_q2_osovskoy.domain.entity.ViewState
import com.example.a2022_q2_osovskoy.domain.use_case.PersonsLoadUseCase
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(private val personsLoadUseCase: PersonsLoadUseCase) : ViewModel() {

    private val _screenState = MutableLiveData<ViewState>()
    val screenState = _screenState

    private val _colorState = MutableLiveData<ViewColorState>()
    val colorState = _colorState

    fun obtainEvent(event: ViewEvent) = when (event) {
        is ViewEvent.Typing -> {
            fetchPersons(event.text)
        }
        is ViewEvent.Click -> {
            changeColor()
        }
    }

    private fun fetchPersons(startWith: String) = viewModelScope.launch {
        _screenState.value = ViewState.Loading
        val result = personsLoadUseCase.load(startWith)
        _screenState.value = when {
            result.isEmpty() -> ViewState.Empty
            else -> ViewState.Success(result)
        }
    }

    private fun changeColor() = viewModelScope.launch {
        _colorState.value = try {
            ViewColorState.Success(
                color = Color.argb(BASE_COLOR,
                    Random.nextInt(UNTIL),
                    Random.nextInt(UNTIL),
                    Random.nextInt(UNTIL)))
        } catch (e: RuntimeException) {
            ViewColorState.Error
        }
    }

    companion object {
        const val BASE_COLOR = 255
        const val UNTIL = 256
    }
}