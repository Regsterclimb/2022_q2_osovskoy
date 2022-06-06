package com.example.a2022_q2_osovskoy.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entities.MyAnimal
import com.example.a2022_q2_osovskoy.domain.usecase.GetAnimalsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getAnimalsUseCase: GetAnimalsUseCase,
) : ViewModel() {

    private val _result = MutableLiveData<List<MyAnimal>>()
    val result: LiveData<List<MyAnimal>> = _result

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("MainViewModel", "Failed to load animals and prices", exception)
    }

    init {
        viewModelScope.launch(handler) {
            handleAnimalPrices(getAnimalsUseCase())
        }
    }

    private fun handleAnimalPrices(animalToPriceMap: List<MyAnimal>) {
        _result.value = animalToPriceMap
    }
}