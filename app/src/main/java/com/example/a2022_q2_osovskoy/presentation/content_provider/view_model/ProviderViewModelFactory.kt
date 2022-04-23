package com.example.a2022_q2_osovskoy.presentation.content_provider.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.presentation.App
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ProviderViewModelFactory(private val applicationContext: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            ProviderMainViewModel::class.java -> ProviderMainViewModel(useCase = (applicationContext as App).useCase)
            else -> throw IllegalArgumentException("Wrong Model Class")
        }
        return viewModel as T
    }
}