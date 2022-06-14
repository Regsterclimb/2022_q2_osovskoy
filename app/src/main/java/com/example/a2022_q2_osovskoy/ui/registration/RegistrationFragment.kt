package com.example.a2022_q2_osovskoy.ui.registration

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.RegistrationFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.*
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.registration.RegEvent
import com.example.a2022_q2_osovskoy.presentation.registration.RegistrationViewModel
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class RegistrationFragment : DaggerFragment(R.layout.registration_fragment) {

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[RegistrationViewModel::class.java]
    }

    private val binding by viewBinding(RegistrationFragmentBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            regNameInput.clearErrorOnAnyInput()
            regPasswordInput.clearErrorOnAnyInput()
            regNameEdit.onFocusChange { hideKeyBoard(requireContext(), view) }
            regPasswordEdit.onFocusChange { hideKeyBoard(requireContext(), view) }
        }
        setUpRegistrationButton()
        setAuthTextClick()
        viewModel.regEvent.observe(viewLifecycleOwner, ::handleRegState)
    }

    private fun handleRegState(event: RegEvent) {
        when (event) {
            RegEvent.Loading -> loadingEvent(true)
            RegEvent.InputError.Name -> binding.regNameInput.showErrorResId(R.string.inputName)
            RegEvent.InputError.Password -> binding.regPasswordInput.showErrorResId(R.string.inputPassword)
            is RegEvent.Success -> navigateToAuth()
            is RegEvent.Error -> errorEvent()
        }
    }

    private fun navigateToAuth() {
        navigate(NavCommand(
            NavCommands.DeepLink(
                Uri.parse(NavDestination.DEEP_AUTH),
                isModal = true,
                isSingleTop = true))
        )
    }

    private fun setUpRegistrationButton() {
        with(binding) {
            regButton.setOnClickListener {
                viewModel.tryReg(
                    name = regNameInput.getTrimmedText(),
                    password = regPasswordInput.getTrimmedText()
                )
            }
        }
    }

    private fun setAuthTextClick() {
        binding.authText.setOnClickListener {
            navigate(NavCommand(
                NavCommands.DeepLink(
                    Uri.parse(NavDestination.DEEP_AUTH),
                    isModal = true,
                    isSingleTop = true))
            )
        }
    }

    private fun errorEvent() {
        binding.regNameInput.showErrorResId(R.string.regError)
        loadingEvent(false)
    }

    private fun loadingEvent(isLoading: Boolean) {
        with(binding) {
            regContainer.isVisible = !isLoading
            regProgressBar.isVisible = isLoading
        }
        hideKeyBoard(requireContext(), view)
    }
}