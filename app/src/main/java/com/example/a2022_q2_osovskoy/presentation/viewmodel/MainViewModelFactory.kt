package com.example.a2022_q2_osovskoy.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.presentation.App

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ItemsViewModel::class.java -> ItemsViewModel(itemsUseCase = (applicationContext as App).itemsUseCase)
            else -> throw IllegalStateException("some wrong modelClass")
        }
        return viewModel as T
    }
}