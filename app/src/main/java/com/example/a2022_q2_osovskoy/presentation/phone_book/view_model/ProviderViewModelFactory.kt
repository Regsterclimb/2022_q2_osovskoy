package com.example.a2022_q2_osovskoy.presentation.phone_book.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.presentation.App
import com.example.a2022_q2_osovskoy.presentation.add_person.view_model.PersonAdderViewModel

@Suppress("UNCHECKED_CAST")
class ProviderViewModelFactory(private val applicationContext: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        applicationContext as App
        with(applicationContext) {
            val viewModel = when (modelClass) {
                ProviderMainViewModel::class.java -> ProviderMainViewModel(
                    useCase = personUseCase,
                    personDataBaseRepository = personRepositoryImpl)
                PersonAdderViewModel::class.java -> PersonAdderViewModel(
                    personDataBaseRepository = personRepositoryImpl
                )
                else -> throw IllegalArgumentException("Wrong Model Class")
            }
            return viewModel as T
        }
    }

}