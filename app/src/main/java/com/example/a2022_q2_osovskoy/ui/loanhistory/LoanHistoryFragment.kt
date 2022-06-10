package com.example.a2022_q2_osovskoy.ui.loanhistory

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanHistoryFragmentBinding
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.history.LoansHistoryViewModel
import com.example.a2022_q2_osovskoy.presentation.history.LoansState
import com.example.a2022_q2_osovskoy.ui.loandetail.LoanDetailFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoanHistoryFragment : DaggerFragment(R.layout.loan_history_fragment) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LoansHistoryViewModel::class.java]
    }

    private val binding by viewBinding(LoanHistoryFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            loansRecycler.adapter = LoansAdapter { navigateToDetails(it) }
            loansSwipeRefresh.apply {
                setOnRefreshListener {
                    viewModel.refreshLoans()
                    isRefreshing = false
                }
            }
            button.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

        viewModel.loansState.observe(viewLifecycleOwner) { loansState ->
            when (loansState) {
                is LoansState.Success -> {
                    (binding.loansRecycler.adapter as LoansAdapter).submitList(loansState.loans)
                }
                is LoansState.Error -> {}
                is LoansState.Empty -> {}
                is LoansState.Loading -> {}
            }
        }
    }

    private fun navigateToDetails(loansId: Long) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.activityContainer, LoanDetailFragment())
            .addToBackStack(null)
            .commit()
    }
}