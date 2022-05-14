package com.example.a2022_q2_osovskoy.ui.core

import androidx.core.view.isVisible
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentPhoneBookBinding

class PersonEventDisplay(private val binding: FragmentPhoneBookBinding) {

    fun showLoading() = with(binding) {
        progressBar.isVisible = true
        errorText.isVisible = false
        personsRecycler.isVisible = false
    }

    fun showSuccess() = with(binding) {
        errorText.isVisible = false
        progressBar.isVisible = false
        personsRecycler.isVisible = true
    }

    fun showEmpty() = with(binding) {
        errorText.apply {
            setText(R.string.emptyText)
            isVisible = true
        }
        progressBar.isVisible = false
        personsRecycler.isVisible = false
    }

    fun showError() = with(binding) {
        errorText.apply {
            setText(R.string.errorText)
            isVisible = true
        }
        progressBar.isVisible = false
        personsRecycler.isVisible = false
    }
}