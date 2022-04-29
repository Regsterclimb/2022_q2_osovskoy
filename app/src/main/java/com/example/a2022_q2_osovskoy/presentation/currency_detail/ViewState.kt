package com.example.a2022_q2_osovskoy.presentation.currency_detail

import androidx.core.view.isVisible
import com.example.a2022_q2_osovskoy.databinding.CurrencyFragmentBinding

interface ViewState {

    fun showLoading()

    fun showSuccess()

    fun showError()

    class Currency(private val binding: CurrencyFragmentBinding) : ViewState {
        override fun showLoading() {
            with(binding) {
                currencyContainer.isVisible = false
                currencyFragmentLoader.isVisible = true
            }
        }

        override fun showSuccess() {
            with(binding){
                currencyContainer.isVisible = true
                currencyFragmentLoader.isVisible = false
            }
        }

        override fun showError() {
            with(binding){
                currencyContainer.isVisible = true
                currencyFragmentLoader.isVisible = false
            }
        }
    }
    class Result(private val binding: CurrencyFragmentBinding) :ViewState {
        override fun showLoading() {
            with(binding){
                currencyLoader.isVisible = true
                result.isVisible = false
                resultCurrencyNumber.isVisible = false
            }
        }

        override fun showSuccess() {
            with(binding){
                currencyLoader.isVisible = false
                result.isVisible = true
                resultCurrencyNumber.isVisible = true
            }
        }

        override fun showError() {
            with(binding){
                currencyLoader.isVisible = false
                result.isVisible = true
                resultCurrencyNumber.isVisible = false
            }
        }
    }
}