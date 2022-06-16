package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanConditionFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.extentions.hide
import com.example.a2022_q2_osovskoy.extentions.show
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.loanrequest.Instruction
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanConditionState
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanConditionViewModel
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoanConditionFragment : DaggerFragment(R.layout.loan_condition_fragment) {

    private val binding by viewBinding(LoanConditionFragmentBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[LoanConditionViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupConditionRecycler()
        setupViews()
        viewModel.updateAppConfig(AppConfig.BASE)
        viewModel.loanCondition.observe(viewLifecycleOwner, ::handleLoanConditionState)
        viewModel.instructionState.observe(viewLifecycleOwner, ::handleInstruction)
    }

    private fun handleLoanConditionState(state: LoanConditionState) {
        when (state) {
            is LoanConditionState.Success -> {
                showSuccess(state.loanCondition)
            }
            is LoanConditionState.Error -> handleConditionsStateErrors(state)
            LoanConditionState.Loading -> showLoading()
        }
    }

    private fun handleConditionsStateErrors(stateError: LoanConditionState.Error) {
        when (stateError) {
            is LoanConditionState.Error.BadRequest -> setErrorText(R.string.badRequestError)
            LoanConditionState.Error.Forbidden -> setErrorText(R.string.forbiddenError)
            LoanConditionState.Error.NotFound -> setErrorText(R.string.notFoundError)
            LoanConditionState.Error.ServerIsNotResponding -> setErrorText(R.string.serverIsNotRespondingError)
            LoanConditionState.Error.Unauthorized -> setErrorText(R.string.serverIsNotRespondingError)
            LoanConditionState.Error.NoInternetConnection -> setErrorText(R.string.noInternetError)
            LoanConditionState.Error.Unknown -> setErrorText(R.string.unknownError)
        }
        showError()
    }

    private fun setErrorText(@StringRes id: Int) {
        binding.conditionErrorText.apply {
            setText(id)
            show()
        }
    }

    private fun setupConditionRecycler() {
        binding.loanConditionRecycler.apply {
            adapter = getLoanConditionAdapter()
            itemAnimator = null
        }
    }

    private fun showLoading() {
        with(binding) {
            loanConditionRecycler.hide()
            conditionErrorText.hide()
            conditionProgressBar.show()
        }
    }

    private fun showError() {
        with(binding) {
            loanConditionRecycler.hide()
            conditionErrorText.show()
            conditionProgressBar.hide()
        }
    }

    private fun showSuccess(loanCondition: LoanCondition) {
        with(binding) {
            loanConditionRecycler.show()
            conditionErrorText.hide()
            conditionProgressBar.hide()
            (loanConditionRecycler.adapter as LoanConditionAdapter).submitList(
                listOf(loanCondition)
            )
        }
    }

    private fun getLoanConditionAdapter(): LoanConditionAdapter =
        LoanConditionAdapter { condition ->
            val requestDirection =
                LoanConditionFragmentDirections
                    .actionLoanConditionFragmentToLoanRequestFragment(
                        condition.maxAmount,
                        condition.percent.toString(),
                        condition.period
                    )
            findNavController().navigate(requestDirection)
        }

    private fun setupViews() {
        with(binding) {
            openHistory.setOnClickListener {
                navigateToHistory()
            }
            loanConditionSwipeRefresh.apply {
                setOnRefreshListener {
                    viewModel.refreshConditions()
                    isRefreshing = false
                }
            }
        }
    }

    private fun handleInstruction(instruction: Instruction) {
        if (instruction.show){
            binding.instructionValue.show()
        } else
        {
            binding.instructionValue.hide()
        }
        setupInstructionButton(instruction.show)
    }

    private fun setupInstructionButton(show: Boolean) {
        with(binding) {
            showInstructionButton.setOnClickListener {
                if (show) {
                    viewModel.showInstruction(false)
                } else {
                    viewModel.showInstruction(true)
                }
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
}