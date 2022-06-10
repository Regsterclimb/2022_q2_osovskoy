package com.example.a2022_q2_osovskoy.ui.auth

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.AuthFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.clearErrorOnAnyInput
import com.example.a2022_q2_osovskoy.extentions.getTrimmedText
import com.example.a2022_q2_osovskoy.extentions.showErrorResId
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.auth.AuthState
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
            regNameInput.clearErrorOnAnyInput()
            regPasswordInput.clearErrorOnAnyInput()
        }

        setUpAuthButton()
        setUpRegistrationText()
        viewModel.authState.observe(viewLifecycleOwner, ::handleAuthState)
    }

    //todo() обнулить текст и ошибки при смене экрана + ресурсы
    //SingleLiveEvent
    private fun handleAuthState(state: AuthState) {
        when (state) {
            is AuthState.Loading -> loadingEvent(true)
            is AuthState.Success -> {
                navigateForward()
            }
            is AuthState.Error -> {
                loadingEvent(false)
                binding.regNameInput.showErrorResId(R.string.authError)
                Toast.makeText(requireContext(), "Login Error", Toast.LENGTH_SHORT).show()
            }
            is AuthState.InputError.Name -> {
                binding.regNameInput.showErrorResId(R.string.inputName)
            }
            is AuthState.InputError.Password -> {
                binding.regPasswordInput.showErrorResId(R.string.inputPassword)
            }
        }
    }

    private fun navigateForward() {
        navigate(NavCommand(
            NavCommands.DeepLink(
                Uri.parse(NavDestination.DEEP_CONDITION),
                isModal = true,
                isSingleTop = true))
        )
    }

    private fun setUpAuthButton() {
        with(binding) {
            regButton.setOnClickListener {
                viewModel.tryAuth(
                    name = regNameInput.getTrimmedText(),
                    password = regPasswordInput.getTrimmedText()
                )
            }
        }
    }

    //todo() button mb
    private fun setUpRegistrationText() {
        with(binding) {
            registrationText.setOnClickListener {
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
    }

    private fun loadingEvent(isLoading: Boolean) {
        with(binding) {
            authContainer.isVisible = !isLoading
            registrationText.isVisible = !isLoading
            authProgressBar.isVisible = isLoading
        }
    }

    private fun showToast(@StringRes value: Int) =
        Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
}