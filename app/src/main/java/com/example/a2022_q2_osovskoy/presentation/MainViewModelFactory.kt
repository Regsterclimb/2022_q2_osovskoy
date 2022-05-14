package com.example.a2022_q2_osovskoy.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.presentation.add_person.AddPersonViewModel
import com.example.a2022_q2_osovskoy.presentation.base_phone_book.BaseViewModel
import com.example.a2022_q2_osovskoy.presentation.file_phone_book.FileBookViewModel
import com.example.a2022_q2_osovskoy.ui.App

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val applicationContext: Context) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        applicationContext as App

        with(applicationContext) {
            val viewModel = when (modelClass) {
                BaseViewModel::class.java -> BaseViewModel(
                    personsFirstUploadUseCase = personsFirstUploadUseCase,
                    personsLoaderUseCase = personsLoaderUseCase,
                    personsRemoverUseCase = personsRemoverUseCase,
                    personRemoveUseCase = personRemoveUseCase)

                AddPersonViewModel::class.java -> AddPersonViewModel(
                    personCreateUseCase = personCreateUseCase,
                    personUpdateUseCase = personUpdateUseCase)

                FileBookViewModel::class.java -> FileBookViewModel(
                    fileFirstUploadUseCase = fileFirstUploadUseCase,
                    filePersonsLoaderUseCase = filePersonsLoaderUseCase,
                    filePersonsRemoverUseCase = filePersonsRemoverUseCase
                )
                else -> throw IllegalArgumentException("Wrong Model Class")
            }
            return viewModel as T
        }
    }
}