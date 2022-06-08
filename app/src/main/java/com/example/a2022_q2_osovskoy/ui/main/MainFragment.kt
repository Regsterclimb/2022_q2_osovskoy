package com.example.a2022_q2_osovskoy.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.MainFragmentBinding
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.main.MainViewModel
import com.example.a2022_q2_osovskoy.ui.auth.AuthFragment
import com.example.a2022_q2_osovskoy.ui.loanhistory.LoanHistoryFragment
import com.example.a2022_q2_osovskoy.ui.loanrequest.LoanRequestFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MainFragment : DaggerFragment(R.layout.main_fragment) {

    private val viewBinding by viewBinding(MainFragmentBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.changeAppConfig(true)

        viewModel.loanCondition.observe(viewLifecycleOwner) {
            with(viewBinding) {
                loanConditionAmount.text = it.maxAmount.toString()
                loanConditionPercent.text = it.percent.toString()
                loanConditionPeriod.text = it.period.toString()
            }
        }

        viewBinding.logoutButton.setOnClickListener {
            viewModel.changeAppConfig(false)
            parentFragmentManager.beginTransaction()
                .replace(R.id.activityContainer, AuthFragment()).commit()
        }
        viewBinding.openHistory.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.activityContainer, LoanHistoryFragment())
                .addToBackStack(null)
                .commit()
        }

        viewBinding.openLoanScreenButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.activityContainer, LoanRequestFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}