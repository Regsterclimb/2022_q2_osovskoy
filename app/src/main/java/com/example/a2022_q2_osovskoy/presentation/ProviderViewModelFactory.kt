package com.example.a2022_q2_osovskoy.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.presentation.add_person.view_model.PersonAdderViewModel
import com.example.a2022_q2_osovskoy.presentation.file_phone_book.FileBookViewModel
import com.example.a2022_q2_osovskoy.presentation.phone_book.view_model.ProviderMainViewModel

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
                FileBookViewModel::class.java -> FileBookViewModel(
                    fileUseCase = filePersonUseCase
                )
                else -> throw IllegalArgumentException("Wrong Model Class")
            }
            return viewModel as T
        }
    }

}