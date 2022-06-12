package com.example.a2022_q2_osovskoy.ui.loanrequest

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoanSuccessFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.provideOnBackPressedCallBack
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
import dagger.android.support.DaggerFragment


class LoanSuccessFragment : DaggerFragment(R.layout.loan_success_fragment) {

    private val binding by viewBinding(LoanSuccessFragmentBinding::bind)

    private val args: LoanSuccessFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            provideOnBackPressedCallBack { navigateToHistory() }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            navToHistoryButton.setOnClickListener {
                navigateToHistory()
            }
            navToDetailsButton.setOnClickListener {
                navigateToDetails(args.id)
            }
        }
    }

    private fun navigateToDetails(loanId: Long) {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse(String.format(NavDestination.DEEP_DETAILS + "/$loanId")),
                    isModal = true,
                    isSingleTop = false
                )
            )
        )
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
}