package com.example.a2022_q2_osovskoy.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.data.storage.ItemsData
import com.example.a2022_q2_osovskoy.domain.useCase.ItemsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val itemsUseCase: ItemsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main,
) : ViewModel() {

    private var _itemsEvents = MutableLiveData<ItemsEvent>()
    val itemEvents = _itemsEvents

    fun loadItems() {
        viewModelScope.launch(dispatcher) {
            when (val resultState = itemsUseCase.loadItems()) {
                is ItemsUseCase.ResultState.Success -> {
                    _itemsEvents.value = ItemsEvent.Success(resultState.result)
                }
                is ItemsUseCase.ResultState.Error -> {
                    _itemsEvents.value = ItemsEvent.Error
                }
            }
        }
    }

    sealed class ItemsEvent {
        class Success(val result: List<ItemsData.ListItem>) : ItemsEvent()
        object Error : ItemsEvent()
    }

}