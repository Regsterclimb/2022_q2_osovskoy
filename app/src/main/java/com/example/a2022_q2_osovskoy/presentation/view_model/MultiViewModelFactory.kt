package com.example.a2022_q2_osovskoy.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class MultiViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModelFactories.getValue(modelClass as Class<ViewModel>).get() as T

    val viewModelsClasses get() = viewModelFactories.keys.map { clazz -> clazz.hashCode() }
}