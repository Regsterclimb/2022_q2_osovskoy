package com.example.a2022_q2_osovskoy.ui.loanhistory

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanHistoryFragmentBinding
import com.example.a2022_q2_osovskoy.domain.entity.AppConfig
import com.example.a2022_q2_osovskoy.extentions.hide
import com.example.a2022_q2_osovskoy.extentions.show
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.loanhistory.LoansHistoryViewModel
import com.example.a2022_q2_osovskoy.presentation.loanhistory.LoansState
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
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
            setUpListeners(this)
            loansRecycler.adapter = LoansAdapter {
                navigateToDetails(it)
            }
            loansSwipeRefresh.apply {
                setOnRefreshListener {
                    viewModel.refreshLoans()
                    isRefreshing = false
                }
            }
        }
        viewModel.loansState.observe(viewLifecycleOwner, ::handleLoansState)
    }

    private fun handleLoansState(state: LoansState) {
        when (state) {
            is LoansState.Success -> handleSuccessState(state)
            is LoansState.Error -> handleStateErrors(state)
            is LoansState.Empty -> showListEmpty()
            is LoansState.Loading -> showLoading()
        }
    }

    private fun handleSuccessState(successState: LoansState.Success) {
        with(binding) {
            when (successState) {
                is LoansState.Success.Local -> {
                    (loansRecycler.adapter as LoansAdapter).submitList(successState.localLoans)
                    historyError.show()
                }
                is LoansState.Success.Remote -> {
                    (loansRecycler.adapter as LoansAdapter).submitList(successState.remoteLoans)
                    historyError.hide()
                }
            }
            showSuccess()
        }
    }

    private fun handleStateErrors(stateError: LoansState.Error) {
        when (stateError) {
            is LoansState.Error.BadRequest -> setErrorText(R.string.badRequestError)
            LoansState.Error.Forbidden -> setErrorText(R.string.forbiddenError)
            LoansState.Error.NotFound -> setErrorText(R.string.notFoundError)
            LoansState.Error.ServerIsNotResponding -> setErrorText(R.string.serverIsNotRespondingError)
            LoansState.Error.Unauthorized -> setErrorText(R.string.serverIsNotRespondingError)
            LoansState.Error.Unknown -> setErrorText(R.string.unknownError)
            LoansState.Error.NoInternetConnection -> handleNoInternetState()
        }
        showError()
    }

    private fun setErrorText(@StringRes id: Int) {
        binding.historyError.apply {
            setText(id)
            show()
        }
    }

    private fun handleNoInternetState() {
        setErrorText(R.string.noInternetError)
        viewModel.getLocalLoans()
    }

    private fun showError() {
        with(binding) {
            loansRecycler.hide()
            historyError.show()
            historyProgressBar.hide()
        }
    }

    private fun showSuccess() {
        with(binding) {
            loansRecycler.show()
            loansListError.hide()
            historyProgressBar.hide()
        }
    }

    private fun showListEmpty() {
        with(binding) {
            loansRecycler.hide()
            loansListError.show()
            historyProgressBar.hide()
        }
    }

    private fun showLoading() {
        with(binding) {
            loansRecycler.hide()
            loansListError.hide()
            historyError.hide()
            historyProgressBar.show()
        }
    }

    private fun setUpListeners(binding: LoanHistoryFragmentBinding){
        binding.applyNewLoanButton.setOnClickListener {
            navigateToLoanCondition()
        }
        binding.logoutButton.setOnClickListener {
            viewModel.updateAppConfig(AppConfig.UNAUTHORIZED)
            navigateToAuth()
        }
    }
    private fun navigateToDetails(loansId: Long) {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = (Uri.parse(String.format(NavDestination.DEEP_DETAILS + "/$loansId"))),
                    isModal = false,
                    isSingleTop = false
                )
            )
        )
    }

    private fun navigateToLoanCondition() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = (Uri.parse(NavDestination.DEEP_LOAN_REQUEST)),
                    isModal = true,
                    isSingleTop = false
                )
            )
        )
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