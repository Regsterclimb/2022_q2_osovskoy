package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
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
import com.example.a2022_q2_osovskoy.presentation.loanrequest.LoanRequestState
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
        viewModel.setLoanCondition(args.amount, args.percent, args.period)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextInput()
        viewModel.loanRequestState.observe(viewLifecycleOwner, ::handleLoanRequestState)
        viewModel.loanCondition.observe(viewLifecycleOwner, ::handleLoanCondition)
    }

    private fun handleLoanRequestState(state: LoanRequestState) {
        with(binding) {
            when (state) {
                is LoanRequestState.Success -> handleSuccessEvent(state.loan.id)
                is LoanRequestState.Loading -> handleLoadingEvent(true)
                is LoanRequestState.Error -> handleRequestStateErrors(state)
                LoanRequestState.Typing -> binding.requestErrorText.hide()
                is LoanRequestState.LoanConditionReceived -> {
                    handleLoanCondition(state.loanCondition)
                }
                LoanRequestState.InputError.Name -> loanNameInput.showErrorResId(R.string.inputNameEmpty)
                LoanRequestState.InputError.LastName -> {
                    loanLastNameInput.showErrorResId(R.string.inputLastNameEmpty)
                }
                LoanRequestState.InputError.Phone -> loanPhoneInput.showErrorResId(R.string.inputPhoneEmpty)
            }
        }
    }

    private fun handleRequestStateErrors(stateError: LoanRequestState.Error) {
        when (stateError) {
            is LoanRequestState.Error.BadRequest -> setErrorText(R.string.badRequestError)
            LoanRequestState.Error.Forbidden -> setErrorText(R.string.forbiddenError)
            LoanRequestState.Error.NotFound -> setErrorText(R.string.notFoundError)
            LoanRequestState.Error.Unauthorized -> setErrorText(R.string.serverIsNotRespondingError)
            LoanRequestState.Error.NoInternetConnection -> setErrorText(R.string.noInternetError)
            LoanRequestState.Error.Unknown -> setErrorText(R.string.unknownError)
            LoanRequestState.Error.ServerIsNotResponding -> {
                setErrorText(R.string.serverIsNotRespondingError)
            }
        }
        handleLoadingEvent(false)
    }

    private fun setErrorText(@StringRes id: Int) {
        binding.requestErrorText.apply {
            setText(id)
            show()
        }
    }

    private fun setUpLoanRequestButton(loanCondition: LoanCondition) {
        with(binding) {
            requestLoanButton.setOnClickListener {
                viewModel.trySendRequest(
                    loanCondition = loanCondition,
                    name = loanNameInput.getTrimmedText(),
                    lastName = loanLastNameInput.getTrimmedText(),
                    phone = loanPhoneInput.getTrimmedText(),
                )
            }
        }
    }

    private fun handleLoanCondition(condition: LoanCondition) {
        setUpLoanRequestButton(condition)
        with(binding) {
            requestAmount.apply {
                text = condition.maxAmount.toString()
                append(getString(R.string.addRub))
            }
            requestPercent.apply {
                text = condition.percent.toString()
                append(getString(R.string.addPercent))
            }
            requestPeriod.apply {
                text = condition.period.toString()
                append(getString(R.string.addDays))
            }

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

    private fun setupTextInput() {
        with(binding) {
            loanNameInput.apply {
                clearErrorOnAnyInput()
                setState { viewModel.setTyping() }
                onFocusChange { hideKeyBoard(requireContext(), view) }
            }
            loanLastNameInput.apply {
                clearErrorOnAnyInput()
                setState { viewModel.setTyping() }
                onFocusChange { hideKeyBoard(requireContext(), view) }
            }
            loanPhoneInput.apply {
                clearErrorOnAnyInput()
                setState { viewModel.setTyping() }
                onFocusChange { hideKeyBoard(requireContext(), view) }
            }
        }
    }
}