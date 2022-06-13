package com.example.a2022_q2_osovskoy.ui.loandetail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanDetailsFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.extentions.*
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.loandetail.LoanDetailState
import com.example.a2022_q2_osovskoy.presentation.loandetail.LoanDetailViewModel
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoanDetailFragment : DaggerFragment(R.layout.loan_details_fragment) {

    private val binding by viewBinding(LoanDetailsFragmentBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[LoanDetailViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this,
            provideOnBackPressedCallBack { navigateToHistory() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoan(arguments?.get(LOAN_ID) as Long)
        viewModel.loanState.observe(viewLifecycleOwner, ::handleLoanDetailState)
    }

    private fun handleLoanDetailState(state: LoanDetailState) {
        when (state) {
            is LoanDetailState.Success -> setUpViews(state.loanDetail)
            is LoanDetailState.Approved -> handleApprovedState(state.isApproved)
            is LoanDetailState.Error -> {
                Toast.makeText(requireContext(), R.string.simpleError, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUpViews(loan: Loan) {
        with(binding) {
            detailAmount.text = loan.Long.toString().addRub()
            detailDate.text = loan.date
            detailPercent.text = loan.percent.toString().addPercent()
            detailState.text = loan.state
            detailId.text = loan.id.toString()
        }
    }

    private fun handleApprovedState(loanIsApproved: Boolean) {
        binding.approvalContainer.run {
            if (loanIsApproved) show() else hide()
        }
    }

    private fun navigateToHistory() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse(NavDestination.DEEP_HISTORY),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }

    companion object {
        const val LOAN_ID = "id"
    }
}