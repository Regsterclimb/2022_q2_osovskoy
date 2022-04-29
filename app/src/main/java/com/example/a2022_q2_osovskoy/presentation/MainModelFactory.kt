package com.example.a2022_q2_osovskoy.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.presentation.currency_detail.view_model.CurrencyViewModel
import com.example.a2022_q2_osovskoy.presentation.currency_list.view_model.CurrenciesViewModel

@Suppress("UNCHECKED_CAST")
class MainModelFactory(
    private val applicationContext: Context,
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        applicationContext as App
        with(applicationContext) {
            val viewModel = when (modelClass) {
                CurrenciesViewModel::class.java -> {
                    CurrenciesViewModel(
                        currenciesUseCase = currenciesUseCase,
                        dispatcher = getDispatcherMainImmediate())
                }
                CurrencyViewModel::class.java -> {
                    CurrencyViewModel(
                        currencyUseCase = currencyUseCase,
                        calculationUseCase = calculationUseCase,
                        dispatcher = getDispatcherMainImmediate())
                }
                else
                -> throw IllegalStateException("something wrong modelClass at CurrencyListViewModelFactory")
            }
            return viewModel as T
        }
    }
}