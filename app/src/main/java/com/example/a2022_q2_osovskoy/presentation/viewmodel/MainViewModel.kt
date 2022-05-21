package com.example.a2022_q2_osovskoy.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.usecase.GetStringFromLocalUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.GetStringFromRemoteUseCase
import com.example.a2022_q2_osovskoy.presentation.MainState
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getStringFromLocalUseCase: GetStringFromLocalUseCase,
    private val getStringFromRemoteUseCase: GetStringFromRemoteUseCase,
) : ViewModel() {

    private val _screenState: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val screenState: LiveData<MainState> = _screenState

    fun loadStrings() {
        viewModelScope.launch {
            _screenState.value = MainState.Loading()
            _screenState.value = MainState.Success(
                remoteString = getStringFromRemoteUseCase(),
                localString = getStringFromLocalUseCase()
            )
        }
    }
}