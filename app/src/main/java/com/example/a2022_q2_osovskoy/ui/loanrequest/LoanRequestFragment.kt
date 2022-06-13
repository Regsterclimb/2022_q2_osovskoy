package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanRequestFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanCondition
import com.example.a2022_q2_osovskoy.extentions.*
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanRequestEvent
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanRequestViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoanRequestFragment : DaggerFragment(R.layout.loan_request_fragment) {

    private val binding by viewBinding(LoanRequestFragmentBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[LoanRequestViewModel::class.java]
    }

    private val args: LoanRequestFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.handleLoanCondition(args.amount, args.percent, args.period)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpTextInput()
        setupEditText()
        viewModel.loanRequestEvent.observe(viewLifecycleOwner, ::handleLoanRequestState)
    }

    private fun handleLoanRequestState(event: LoanRequestEvent) {
        with(binding) {
            when (event) {
                is LoanRequestEvent.Success -> handleSuccessEvent(event.loan.id)
                is LoanRequestEvent.Loading -> handleLoadingEvent(true)
                is LoanRequestEvent.Error -> handleLoadingEvent(false)
                is LoanRequestEvent.LoanConditionReceived -> {
                    handleHaveConditionEvent(event.loanCondition)
                }
                LoanRequestEvent.InputError.Name -> loanNameInput.showErrorResId(R.string.inputNameEmpty)
                LoanRequestEvent.InputError.LastName -> {
                    loanLastNameInput.showErrorResId(R.string.inputLastNameEmpty)
                }
                LoanRequestEvent.InputError.Phone -> loanPhoneInput.showErrorResId(R.string.inputPhoneEmpty)
            }
        }
    }

    private fun setUpLoanRequestButton(condition: LoanCondition) {
        with(binding) {
            requestLoanButton.setOnClickListener {
                viewModel.trySendRequest(
                    amount = condition.maxAmount,
                    percent = condition.percent,
                    period = condition.period,
                    name = loanNameInput.getTrimmedText(),
                    lastName = loanLastNameInput.getTrimmedText(),
                    phone = loanPhoneInput.getTrimmedText(),
                )
            }
        }
    }

    private fun handleHaveConditionEvent(condition: LoanCondition) {
        setUpLoanRequestButton(condition)
        with(binding) {
            requestAmount.text = condition.maxAmount.toString().addRub()
            requestPercent.text = condition.percent.toString().addPercent()
            requestPeriod.text = condition.period.toString().addDays()
        }
    }

    private fun handleLoadingEvent(isLoading: Boolean) {
        with(binding) {
            loanRequestContainer.isVisible = !isLoading
            requestProgressBar.isVisible = isLoading
        }
        hideKeyBoard(requireContext(), view)
    }

    private fun handleSuccessEvent(loanId: Long) {
        findNavController().navigate(
            LoanRequestFragmentDirections
                .actionLoanRequestFragmentToLoanSuccessFragment(loanId)
        )
    }

    private fun setUpTextInput() {
        with(binding) {
            loanNameInput.clearErrorOnAnyInput()
            loanLastNameInput.clearErrorOnAnyInput()
            loanPhoneInput.clearErrorOnAnyInput()
        }
    }

    private fun setupEditText() {
        with(binding) {
            loanNameEdit.onFocusChange {
                hideKeyBoard(requireContext(), view)
            }
            loanLastNameEdit.onFocusChange {
                hideKeyBoard(requireContext(), view)
            }
            loanPhoneEdit.onFocusChange {
                hideKeyBoard(requireContext(), view)
            }
        }
    }
}