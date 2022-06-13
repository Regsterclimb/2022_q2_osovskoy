package com.example.a2022_q2_osovskoy.ui.auth

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.AuthFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.*
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.auth.AuthEvent
import com.example.a2022_q2_osovskoy.presentation.auth.AuthViewModel
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavDestination
import com.example.a2022_q2_osovskoy.utils.navigation.navigate
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AuthFragment : DaggerFragment(R.layout.auth_fragment) {

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[AuthViewModel::class.java]
    }

    private val binding by viewBinding(AuthFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            authNameInput.clearErrorOnAnyInput()
            authPasswordInput.clearErrorOnAnyInput()
            authNameEdit.onFocusChange { hideKeyBoard(requireContext(), view) }
            authPasswordEdit.onFocusChange { hideKeyBoard(requireContext(), view) }
        }
        setUpAuthButton()
        setUpRegistrationText()
        viewModel.authEvent.observe(viewLifecycleOwner, ::handleAuthState)
    }

    private fun handleAuthState(event: AuthEvent) {
        when (event) {
            is AuthEvent.Loading -> loadingEvent(true)
            is AuthEvent.Success -> navigateForward()
            is AuthEvent.Error -> {
                loadingEvent(false)
                binding.authNameInput.showErrorResId(R.string.authError)
            }
            is AuthEvent.InputError.Name -> {
                binding.authNameInput.showErrorResId(R.string.inputName)
            }
            is AuthEvent.InputError.Password -> {
                binding.authPasswordInput.showErrorResId(R.string.inputPassword)
            }
        }
    }

    private fun navigateForward() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    Uri.parse(NavDestination.DEEP_LOAN_REQUEST),
                    isModal = true,
                    isSingleTop = true)
            )
        )
    }

    private fun setUpAuthButton() {
        with(binding) {
            authButton.setOnClickListener {
                viewModel.tryAuth(
                    name = authNameInput.getTrimmedText(),
                    password = authPasswordInput.getTrimmedText()
                )
            }
        }
    }

    private fun setUpRegistrationText() {
        binding.registrationText.setOnClickListener {
            navigate(
                NavCommand(
                    NavCommands.DeepLink(
                        url = (Uri.parse(NavDestination.DEEP_REGISTRATION)),
                        isModal = true,
                        isSingleTop = true
                    )
                )
            )
        }
    }

    private fun loadingEvent(isLoading: Boolean) {
        with(binding) {
            authContainer.isVisible = !isLoading
            authProgressBar.isVisible = isLoading
        }
        hideKeyBoard(requireContext(), view)
    }
}