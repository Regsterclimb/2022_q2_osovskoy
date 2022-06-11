package com.example.a2022_q2_osovskoy.ui.loancondition

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanConditionFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.loancondition.LoanConditionState
import com.example.a2022_q2_osovskoy.presentation.loancondition.LoanConditionViewModel
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
                setNavButtonToRequest(state.loanCondition)
            }
            is LoanConditionState.Error -> {}
        }
    }

    private fun setupViews(condition: LoanCondition) {
        with(viewBinding) {
            mainAmount.text = condition.maxAmount.toString()
            mainPercent.text = condition.percent.toString()
            mainPeriod.text = condition.period.toString()
        }
    }

    //todo()
    private fun setUpListeners() {
        with(viewBinding) {
            logoutButton.setOnClickListener {
                viewModel.updateAppConfig(AppConfig.UNAUTHORIZED)
            }
            openHistory.setOnClickListener {
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
            showInstructionButton.setOnClickListener {
                instruction.isVisible = !instruction.isVisible
            }
        }
    }

    private fun setNavButtonToRequest(condition: LoanCondition) {
        viewBinding.openLoanScreenButton.setOnClickListener {
            val deeplink = String.format(
                NavDestination.DEEP_LOAN_REQUEST + "/${condition.maxAmount}/${condition.percent}/${condition.period}"
            )
            navigate(
                NavCommand(
                    NavCommands.DeepLink(
                        url = Uri.parse(deeplink),
                        isModal = false,
                        isSingleTop = false
                    )
                )
            )
        }
    }
}