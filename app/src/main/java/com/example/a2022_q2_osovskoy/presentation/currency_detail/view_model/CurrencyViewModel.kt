package com.example.a2022_q2_osovskoy.presentation.currency_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2022_q2_osovskoy.domain.model.MyCurrency
import com.example.a2022_q2_osovskoy.domain.use_case.CalculationUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.CurrencyUseCase
import com.example.a2022_q2_osovskoy.domain.use_case.ResultState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val currencyUseCase: CurrencyUseCase,
    private val calculationUseCase: CalculationUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
) : ViewModel() {

    sealed class CurrencyResult {
        class Success(val data: MyCurrency) : CurrencyResult()
        object Error : CurrencyResult()
        object IsEmpty : CurrencyResult()
        object Loading : CurrencyResult()
    }

    sealed class ChangeResult {
        class Success(val data: String) : ChangeResult()
        object Error : ChangeResult()
        object IsEmpty : ChangeResult()
        object Loading : ChangeResult()
    }

    private var _myCurrencyEvent = MutableLiveData<CurrencyResult>()
    val myCurrencyEvent: LiveData<CurrencyResult> = _myCurrencyEvent

    private var _resultEvent = MutableLiveData<ChangeResult>()
    val resultEvent: LiveData<ChangeResult> = _resultEvent

    fun getMyCurrency(id: String) {
        viewModelScope.launch(dispatcher) {
            _myCurrencyEvent.value = CurrencyResult.Loading
            _myCurrencyEvent.value =
                when (val resultState = currencyUseCase.load(id)) {
                    is ResultState.Success -> {
                        val data = resultState.data
                        if (data != null) {
                            CurrencyResult.Success(data = data)
                        } else {
                            CurrencyResult.IsEmpty
                        }
                    }
                    is ResultState.Error -> CurrencyResult.Error
                }
        }
    }

    fun changeCurrency(input: String, value: Double) {
        viewModelScope.launch(dispatcher) {
            _resultEvent.value = ChangeResult.Loading
            _resultEvent.value = when (val calcState = calculationUseCase.calculate(input, value)) {
                is ResultState.Success -> {
                    val data = calcState.data
                    if (data != null) {
                        ChangeResult.Success(data = data)
                    } else {
                        ChangeResult.IsEmpty
                    }
                }
                is ResultState.Error -> ChangeResult.Error
            }
        }
    }
}