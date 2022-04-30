package com.example.a2022_q2_osovskoy.presentation.file_phone_book

import android.annotation.SuppressLint
import androidx.core.view.isVisible
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentPhoneBookBinding
import com.example.a2022_q2_osovskoy.presentation.phone_book.PersonAdapter

interface FilePersonEventViewer {

    fun show(personEvent: FileBookViewModel.PersonEvent)

    class Base(private val binding: FragmentPhoneBookBinding) : FilePersonEventViewer {

        @SuppressLint("NotifyDataSetChanged")
        override fun show(personEvent: FileBookViewModel.PersonEvent) {
            with(binding) {
                when (personEvent) {
                    is FileBookViewModel.PersonEvent.Success -> {
                        (personsRecycler.adapter as PersonAdapter).apply {
                            submitList(personEvent.result)
                        }
                        progressBar.isVisible = false
                        personsRecycler.isVisible = true
                    }
                    is FileBookViewModel.PersonEvent.Loading -> {
                        progressBar.isVisible = true
                        errorText.isVisible = false
                        personsRecycler.isVisible = false
                    }
                    FileBookViewModel.PersonEvent.Empty -> {
                        errorText.apply {
                            setText(R.string.emptyText)
                            isVisible = true
                        }
                        progressBar.isVisible = false
                        personsRecycler.isVisible = false
                    }
                    FileBookViewModel.PersonEvent.Error -> {
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