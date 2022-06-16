package com.example.a2022_q2_osovskoy.ui.registration

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.RegistrationFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.*
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.registration.RegState
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
            setupTextInput(this)
            setUpRegistrationButton(this)
            setAuthTextClick(this)
        }
        viewModel.regState.observe(viewLifecycleOwner, ::handleRegState)
    }

    private fun handleRegState(state: RegState) {
        when (state) {
            RegState.Loading -> loadingEvent(true)
            RegState.InputError.Name -> binding.regNameInput.showErrorResId(R.string.inputName)
            RegState.InputError.Password -> binding.regPasswordInput.showErrorResId(R.string.inputPassword)
            RegState.Typing -> binding.regErrorText.hide()
            is RegState.Success -> navigateToAuth()
            is RegState.Error -> handleRegStateErrors(state)
        }
    }

    private fun handleRegStateErrors(stateError: RegState.Error) {
        when (stateError) {
            is RegState.Error.BadRequest -> setErrorText(R.string.badRequestError)
            RegState.Error.Forbidden -> setErrorText(R.string.forbiddenError)
            RegState.Error.NotFound -> setErrorText(R.string.notFoundError)
            RegState.Error.ServerIsNotResponding -> setErrorText(R.string.serverIsNotRespondingError)
            RegState.Error.Unauthorized -> setErrorText(R.string.serverIsNotRespondingError)
            RegState.Error.NoInternetConnection -> setErrorText(R.string.noInternetError)
            RegState.Error.Unknown -> setErrorText(R.string.unknownError)
        }
        loadingEvent(false)
    }

    private fun navigateToAuth() {
        navigate(NavCommand(
            NavCommands.DeepLink(
                Uri.parse(NavDestination.DEEP_AUTH),
                isModal = true,
                isSingleTop = true))
        )
    }

    private fun setUpRegistrationButton(binding: RegistrationFragmentBinding) {
        with(binding) {
            regButton.setOnClickListener {
                viewModel.tryReg(
                    name = regNameInput.getTrimmedText(),
                    password = regPasswordInput.getTrimmedText()
                )
            }
        }
    }

    private fun setAuthTextClick(binding: RegistrationFragmentBinding) {
        binding.authText.setOnClickListener {
            navigate(NavCommand(
                NavCommands.DeepLink(
                    Uri.parse(NavDestination.DEEP_AUTH),
                    isModal = true,
                    isSingleTop = true))
            )
        }
    }

    private fun setErrorText(@StringRes id: Int) {
        binding.regErrorText.apply {
            setText(id)
            show()
        }
    }

    private fun loadingEvent(isLoading: Boolean) {
        with(binding) {
            regContainer.isVisible = !isLoading
            regProgressBar.isVisible = isLoading
        }
        hideKeyBoard(requireContext(), view)
    }

    private fun setupTextInput(binding: RegistrationFragmentBinding) {
        with(binding) {
            regNameInput.apply {
                clearErrorOnAnyInput()
                setState { viewModel.setTyping() }
                onFocusChange { hideKeyBoard(requireContext(), view) }
            }
            regPasswordInput.apply {
                clearErrorOnAnyInput()
                setState { viewModel.setTyping() }
                onFocusChange { hideKeyBoard(requireContext(), view) }
            }
        }
    }
}