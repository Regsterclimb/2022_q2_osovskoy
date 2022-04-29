package com.example.a2022_q2_osovskoy.presentation.currency_list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.model.MyCurrency
import com.example.a2022_q2_osovskoy.domain.use_case.CurrenciesUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CurrenciesViewModel(
    private val currenciesUseCase: CurrenciesUseCase,
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
) : ViewModel() {

    private var _currenciesEvent = MutableLiveData<CurrenciesState>()
    val currenciesEvent: LiveData<CurrenciesState> = _currenciesEvent

    init {
        viewModelScope.launch(dispatcher) {
            _currenciesEvent.value = CurrenciesState.Loading
            _currenciesEvent.value = when (val resultState = currenciesUseCase.load()) {
                is ResultState.Success -> {
                    val data = resultState.data
                    if (data != null) {
                        CurrenciesState.Success(data = data)
                    } else {
                        CurrenciesState.IsEmpty
                    }
                }
                is ResultState.Error -> CurrenciesState.Error
            }
        }
    }

    sealed class CurrenciesState {
        class Success(val data: List<MyCurrency>) : CurrenciesState()
        object Loading : CurrenciesState()
        object Error : CurrenciesState()
        object IsEmpty : CurrenciesState()
    }
}
