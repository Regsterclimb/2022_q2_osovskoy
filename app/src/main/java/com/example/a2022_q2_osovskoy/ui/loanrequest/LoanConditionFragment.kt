package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanConditionFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanConditionState
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanConditionViewModel
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoanConditionFragment : DaggerFragment(R.layout.loan_condition_fragment) {

    private val viewBinding by viewBinding(LoanConditionFragmentBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[LoanConditionViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateAppConfig(AppConfig.BASE)

        viewModel.loanCondition.observe(viewLifecycleOwner, ::handleLoanConditionState)
        setUpListeners()
    }

    private fun handleLoanConditionState(state: LoanConditionState) {
        when (state) {
            is LoanConditionState.Success -> {
                setupViews(state.loanCondition)
                setupOpenRequestButton(state.loanCondition)
            }
            is LoanConditionState.Error -> {
                Toast.makeText(requireContext(), R.string.simpleError, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupViews(condition: LoanCondition) {
        with(viewBinding) {
            mainAmount.text = condition.maxAmount.toString()
            mainPercent.text = condition.percent.toString()
            mainPeriod.text = condition.period.toString()
        }
    }

    private fun setUpListeners() {
        with(viewBinding) {
            logoutButton.setOnClickListener {
                viewModel.updateAppConfig(AppConfig.UNAUTHORIZED)
                navigateToAuth()
            }

            openHistory.setOnClickListener {
                navigateToHistory()
            }

            showInstructionButton.setOnClickListener {
                instruction.isVisible = !instruction.isVisible
            }
        }
    }

    private fun navigateToHistory() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = (Uri.parse(NavDestination.DEEP_HISTORY)),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }

    private fun setupOpenRequestButton(condition: LoanCondition) {
        val requestDirection =
            LoanConditionFragmentDirections
                .actionLoanConditionFragmentToLoanRequestFragment(
                    condition.maxAmount,
                    condition.percent.toString(),
                    condition.period
                )

        viewBinding.openRequestButton.setOnClickListener {
            findNavController().navigate(requestDirection)
        }
    }

    private fun navigateToAuth() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = (Uri.parse(NavDestination.DEEP_AUTH)),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }
}