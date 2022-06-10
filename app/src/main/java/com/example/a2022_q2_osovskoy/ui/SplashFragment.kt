package com.example.a2022_q2_osovskoy.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.presentation.ConfigState
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.SupportViewModel
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
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
        viewModel.appConfigState.observe(viewLifecycleOwner) { configState ->
            if (savedInstanceState == null) {
                openFragmentIfLogged(
                    getNavDestinationByConfigState(
                        configState
                    )
                )
            }
        }
    }

    private fun openFragmentIfLogged(destination: String) {
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

    private fun getNavDestinationByConfigState(configState: ConfigState): String =
        when (configState) {
            ConfigState.Unauthorized -> NavDestination.DEEP_AUTH
            ConfigState.Uninstructed -> NavDestination.DEEP_CONDITION
            else -> NavDestination.DEEP_HISTORY
        }
}