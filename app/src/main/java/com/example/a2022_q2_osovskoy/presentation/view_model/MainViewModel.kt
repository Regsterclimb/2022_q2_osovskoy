package com.example.a2022_q2_osovskoy.presentation.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.usecase.GetStringFromLocalUseCase
import com.example.a2022_q2_osovskoy.domain.usecase.GetStringFromRemoteUseCase
import com.example.a2022_q2_osovskoy.presentation.MainState
import kotlinx.coroutines.launch
import javax.inject.Inject

//todo()
class MainViewModel @Inject constructor(
    private val getStringFromLocalUseCase: GetStringFromLocalUseCase,
    private val getStringFromRemoteUseCase: GetStringFromRemoteUseCase,
) : ViewModel() {
    //TODO: DI

    private val _state: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    init {
        Log.d("MainViewModel", hashCode().toString())
    }

    fun loadStrings() {
        viewModelScope.launch {
            _state.value = MainState.Loading()

            val fromLocal = getStringFromLocalUseCase()
            val fromRemote = getStringFromRemoteUseCase()

            _state.value = MainState.Success(remoteString = fromRemote, localString = fromLocal)
        }
    }

    override fun hashCode(): Int = super.hashCode()

}