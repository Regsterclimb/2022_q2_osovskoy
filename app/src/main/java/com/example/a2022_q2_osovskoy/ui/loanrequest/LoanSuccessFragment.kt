package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanSuccessFragmentBinding
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import javax.inject.Inject

class LoanSuccessFragment : Fragment(R.layout.loan_success_fragment) {

    private val binding by viewBinding(LoanSuccessFragmentBinding::bind)

    @Inject
    lateinit var multiVIewModelFactory: MultiViewModelFactory

    private val viewModel by lazy { ViewModelProvider(this, multiVIewModelFactory) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            loanBackButton.setOnClickListener {
                findNavController().popBackStack()
            }
            loanOpenHistoryButton.setOnClickListener {

            }
        }
    }
}