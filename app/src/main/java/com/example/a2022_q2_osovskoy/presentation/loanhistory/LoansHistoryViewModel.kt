package com.example.a2022_q2_osovskoy.presentation.loanhistory

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.entity.ResultState
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
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
            _loansState.value = handleResultState(getLoansUseCase())
        }
    }
    //todo() обработать во фрагменте
    private fun handleResultState(state: ResultState<List<Loan>>): LoansState = when (state) {
        is ResultState.Success -> LoansState.Success(state.data)
        is ResultState.Error -> LoansState.Error
    }
}