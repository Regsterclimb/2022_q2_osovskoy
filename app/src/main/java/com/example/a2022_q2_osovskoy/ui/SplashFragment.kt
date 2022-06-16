package com.example.a2022_q2_osovskoy.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.presentation.AppConfigEvent
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.SupportViewModel
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SplashFragment : DaggerFragment(R.layout.splash_fragment) {

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[SupportViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.appAppConfigEvent.observe(viewLifecycleOwner) { event ->
                navigateOnEvent(event)
        }
    }

    private fun openFragmentOnEvent(destination: String) {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = (Uri.parse(destination)),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }

    private fun navigateOnEvent(appConfigEvent: AppConfigEvent) = when (appConfigEvent) {
            is AppConfigEvent.NavigateToLoanRequest-> openFragmentOnEvent(appConfigEvent.navDest)
            is AppConfigEvent.NavigateToRegistration -> openFragmentOnEvent(appConfigEvent.navDest)
            is AppConfigEvent.NavigateToHistory -> openFragmentOnEvent(appConfigEvent.navDest)
        }
}