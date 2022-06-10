package com.example.a2022_q2_osovskoy.ui.loandetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanDetailsFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.loan.Loan
import com.example.a2022_q2_osovskoy.extentions.addText
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.loandetail.LoanDetailState
import com.example.a2022_q2_osovskoy.presentation.loandetail.LoanDetailViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoanDetailFragment : DaggerFragment(R.layout.loan_details_fragment) {

    private val binding by viewBinding(LoanDetailsFragmentBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[LoanDetailViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoan(58)

        viewModel.loanState.observe(viewLifecycleOwner, ::handleLoanDetailState)
    }

    private fun handleLoanDetailState(state: LoanDetailState) {
        when (state) {
            is LoanDetailState.Success -> setUpViews(state.loanDetail)
            is LoanDetailState.Error -> {}
        }
    }

    private fun setUpViews(loan: Loan) {
        with(binding) {
            amount.addText(loan.amount.toString())
            date.addText(loan.date)
            percent.addText(loan.percent.toString())
            state.addText(loan.state)
            state.addText(loan.id.toString())
        }
    }
}