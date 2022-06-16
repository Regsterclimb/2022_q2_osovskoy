package com.example.a2022_q2_osovskoy.ui.loandetail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanDetailsFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.loan.LoanDetail
import com.example.a2022_q2_osovskoy.extentions.changeStatus
import com.example.a2022_q2_osovskoy.extentions.hide
import com.example.a2022_q2_osovskoy.extentions.provideOnBackPressedCallBack
import com.example.a2022_q2_osovskoy.extentions.show
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
        binding.loanDetailsSwipeRefresh.apply {
            setOnRefreshListener {
                viewModel.getRemoteLoan(arguments?.get(LOAN_ID) as Long)
                isRefreshing = false
            }
        }
        viewModel.getRemoteLoan(arguments?.get(LOAN_ID) as Long)
        viewModel.loanState.observe(viewLifecycleOwner, ::handleLoanDetailState)
    }

    private fun handleLoanDetailState(state: LoanDetailState) {
        when (state) {
            is LoanDetailState.Success -> handleSuccess(state)
            is LoanDetailState.Approved -> handleApprovedState(state.isApproved)
            is LoanDetailState.Error -> handleErrors(state)
            LoanDetailState.Loading -> showLoading()
        }
    }

    private fun handleSuccess(state: LoanDetailState.Success) {
        Log.d("handleSuccess", state.toString())
        showSuccess()
        with(binding) {
            when (state) {
                is LoanDetailState.Success.Remote -> {
                    setUpViews(state.remoteLoanDetail)
                    detailsError.hide()
                }
                is LoanDetailState.Success.Local -> {
                    setUpViews(state.localLoanDetail)
                    detailsError.show()
                }
            }
        }
    }

    private fun handleErrors(stateError: LoanDetailState.Error) {
        when (stateError) {
            is LoanDetailState.Error.BadRequest -> setErrorText(R.string.badRequestError)
            LoanDetailState.Error.Forbidden -> setErrorText(R.string.forbiddenError)
            LoanDetailState.Error.NotFound -> setErrorText(R.string.notFoundError)
            LoanDetailState.Error.Unauthorized -> setErrorText(R.string.serverIsNotRespondingError)
            LoanDetailState.Error.Unknown -> setErrorText(R.string.unknownError)
            LoanDetailState.Error.ServerNotResponding -> {
                setErrorText(R.string.serverIsNotRespondingError)
            }
            LoanDetailState.Error.NoInternetConnection -> {
                handleNoInternetState(arguments?.get(LOAN_ID) as Long)
            }
        }
        showError()
    }

    private fun setErrorText(@StringRes id: Int) {
        binding.detailsError.apply {
            setText(id)
            show()
        }
    }

    private fun handleNoInternetState(id: Long) {
        setErrorText(R.string.noInternetError)
        viewModel.getLocalLoan(id)
    }

    private fun showError() {
        with(binding) {
            detailsError.show()
            detailsProgressBar.hide()
        }
    }

    private fun showSuccess() {
        with(binding) {
            loanInfoTable.show()
            detailsError.hide()
            detailsProgressBar.hide()
        }
    }

    private fun showLoading() {
        with(binding) {
            loanInfoTable.hide()
            detailsError.hide()
            approvalContainer.hide()
            detailsProgressBar.show()
        }
    }

    private fun setUpViews(loan: LoanDetail) {
        with(binding) {
            detailAmount.text = String.format(loan.amount.toString() + getString(R.string.addRub))
            detailDate.text = loan.date
            detailState.changeStatus(loan.state)
            detailPeriod.apply {
                text = loan.period.toString()
                append(getString(R.string.addDays))
            }
            detailsName.text = loan.name
            detailsLastName.text = loan.lastName
            detailsLastPhone.text = loan.phoneNumber
            detailId.text = loan.id.toString()
            detailPercent.apply {
                text = loan.percent.toString()
                append(getString(R.string.addPercent))
            }
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