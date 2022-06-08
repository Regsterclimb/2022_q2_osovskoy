package com.example.a2022_q2_osovskoy.presentation.history

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.usecase.GetLoansUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoansHistoryViewModel @Inject constructor(private val getLoansUseCase: GetLoansUseCase) :
    ViewModel() {

    private val _loansState = MutableLiveData<LoansState>()
    val loansState: LiveData<LoansState> = _loansState

    init {
        refreshLoans()
    }

    fun refreshLoans() {
        viewModelScope.launch {
            Log.d("LoansHistoryViewModel", getLoansUseCase().toString())
            _loansState.value = LoansState.Success(getLoansUseCase())
        }
    }
}