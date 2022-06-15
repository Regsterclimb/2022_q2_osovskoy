package com.example.a2022_q2_osovskoy.ui.auth

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.AuthFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.*
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
            authNameInput.clearErrorOnAnyInput()
            authPasswordInput.clearErrorOnAnyInput()
            authNameEdit.onFocusChange { hideKeyBoard(requireContext(), view) }
            authPasswordEdit.onFocusChange { hideKeyBoard(requireContext(), view) }
        }
        setUpAuthButton()
        setUpRegistrationText()
        viewModel.authState.observe(viewLifecycleOwner, ::handleAuthState)
    }

    private fun handleAuthState(state: AuthState) {
        when (state) {
            is AuthState.Error -> handleAuthStateErrors(state)
            is AuthState.InputError.Name -> {
                binding.authNameInput.showErrorResId(R.string.inputName)
            }
            is AuthState.InputError.Password -> {
                binding.authPasswordInput.showErrorResId(R.string.inputPassword)
            }
            AuthState.Loading -> loadingEvent(true)
            AuthState.Success -> navigateForward()
            AuthState.Typing -> handleTyping()
        }
    }
    private fun handleAuthStateErrors(stateError: AuthState.Error) {
        when (stateError) {
            is AuthState.Error.BadRequest -> setErrorText(R.string.badRequestError)
            AuthState.Error.Forbidden -> setErrorText(R.string.forbiddenError)
            AuthState.Error.NotFound -> setErrorText(R.string.notFoundError)
            AuthState.Error.ServerIsNotResponding -> setErrorText(R.string.serverIsNotRespondingError)
            AuthState.Error.Unauthorized -> setErrorText(R.string.serverIsNotRespondingError)
        }
        loadingEvent(false)
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
    //вынести в extention
    private fun setErrorText(@StringRes id: Int) {
        binding.authErrorText.apply {
            setText(id)
            show()
        }
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

    private fun handleTyping() {
        with(binding) {
            authNameInput.clearErrorOnAnyInput()
            authPasswordInput.clearErrorOnAnyInput()
            authErrorText.hide()
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