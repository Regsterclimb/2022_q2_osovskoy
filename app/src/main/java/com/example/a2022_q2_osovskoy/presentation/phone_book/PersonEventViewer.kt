package com.example.a2022_q2_osovskoy.presentation.phone_book

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentPhoneBookBinding
import com.example.a2022_q2_osovskoy.presentation.phone_book.view_model.ProviderMainViewModel

interface PersonEventViewer {

    fun show(personEvent: ProviderMainViewModel.PersonEvent)

    class Base(private val binding: FragmentPhoneBookBinding) : PersonEventViewer {

        @SuppressLint("NotifyDataSetChanged")
        override fun show(personEvent: ProviderMainViewModel.PersonEvent) {
            with(binding) {
                when (personEvent) {
                    is ProviderMainViewModel.PersonEvent.Success -> {
                        (personsRecycler.adapter as PersonAdapter).apply {
                            submitList(personEvent.result)
                        }
                        progressBar.isVisible = false
                        personsRecycler.isVisible = true
                    }
                    is ProviderMainViewModel.PersonEvent.Loading -> {
                        progressBar.isVisible = true
                        errorText.isVisible = false
                        personsRecycler.isVisible = false
                    }
                    ProviderMainViewModel.PersonEvent.Empty -> {
                        errorText.apply {
                            setText(R.string.emptyText)
                            isVisible = true
                        }
                        progressBar.isVisible = false
                        personsRecycler.isVisible = false
                    }
                    ProviderMainViewModel.PersonEvent.Error -> {
                        errorText.apply {
                            setText(R.string.errorText)
                            isVisible = true
                        }
                        progressBar.isVisible = false
                        personsRecycler.isVisible = false
                    }
                }
            }
        }
    }
}