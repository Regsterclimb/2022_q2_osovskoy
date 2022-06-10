package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanRequestFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.extentions.getTrimmedText
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            loanConditionAmount.text = arguments?.get("amount").toString()
            loanConditionPercent.text = arguments?.get("percent").toString()
            loanConditionPeriod.text = arguments?.get("period").toString()
        }


        viewModel.loanRequestState.observe(viewLifecycleOwner, ::handleLoanConditionState)
        setUpLoanRequestButton()
    }

    private fun setUpLoanRequestButton() {
        with(binding) {
            binding.requestLoanButton.setOnClickListener {
                viewModel.trySendRequest(
                    loanConditionAmount.text.toString(),
                    loanNameInput.getTrimmedText(),
                    loanLastNameInput.getTrimmedText(),
                    loanPhoneInput.getTrimmedText(),
                    loanConditionPercent.text.toString(),
                    loanConditionPeriod.text.toString()
                )
            }
        }
    }

    private fun handleLoanConditionState(state: LoanRequestState) {
        when (state) {
            is LoanRequestState.Success -> setUpViews(state.loan)

            is LoanRequestState.Error -> {}
            LoanRequestState.InputError.Amount -> TODO()
            LoanRequestState.InputError.LastName -> TODO()
            LoanRequestState.InputError.Name -> TODO()
            LoanRequestState.InputError.Phone -> TODO()
        }
    }

    private fun setUpViews(loan: Loan) {

    }
}