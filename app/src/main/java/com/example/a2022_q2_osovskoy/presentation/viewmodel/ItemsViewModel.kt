package com.example.a2022_q2_osovskoy.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.use_case.GetItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemsViewModel @Inject constructor(private val itemsUseCase: GetItemsUseCase) : ViewModel() {

    private var _itemsState = MutableLiveData<ItemsState>()
    val itemsState = _itemsState

    init {
        viewModelScope.launch {
            _itemsState.value = when (val resultState = itemsUseCase()) {
                is ResultState.Success -> {
                    ItemsState.Success(resultState.result)
                }
                is ResultState.Error -> {
                    ItemsState.Error
                }
            }
        }
    }
}