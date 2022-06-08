package com.example.a2022_q2_osovskoy.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoginFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.getTrimmedText
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.auth.AuthState
import com.example.a2022_q2_osovskoy.presentation.auth.AuthViewModel
import com.example.a2022_q2_osovskoy.presentation.auth.LoginScreenState
import com.example.a2022_q2_osovskoy.ui.main.MainFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class AuthFragment : DaggerFragment(R.layout.login_fragment) {

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[AuthViewModel::class.java]
    }

    private val binding by viewBinding(LoginFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpLoginButton()
        viewModel.authState.observe(viewLifecycleOwner, ::handleAuthState)
        viewModel.loginScreenState.observe(viewLifecycleOwner, ::handleLoginScreenState)
    }

    //todo() обнулить текст при смене экрана + ресурсы
    private fun handleAuthState(state: AuthState) {
        when (state) {
            is AuthState.Registration.Success -> {
                viewModel.changeLoginScreenState(true)
                Toast.makeText(requireContext(), "Registeration success", Toast.LENGTH_SHORT).show()
            }
            is AuthState.Registration.Error -> {
                Toast.makeText(requireContext(), "Registeration Error", Toast.LENGTH_SHORT).show()
            }
            is AuthState.Login.Success -> {
                parentFragmentManager.beginTransaction().replace(R.id.activityContainer,
                    MainFragment()).commit()
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
            }
            is AuthState.Login.Error -> {
                Toast.makeText(requireContext(), "Login Error", Toast.LENGTH_SHORT).show()
            }
            is AuthState.InputError.Name -> showToast(R.string.inputNameEmpty)
            is AuthState.InputError.Password -> showToast(R.string.inputPasswordEmpty)
        }
    }

    private fun handleLoginScreenState(state: LoginScreenState) {
        when (state) {
            is LoginScreenState.Registration -> setRegistrationState()
            is LoginScreenState.UnAuthorized -> setUnauthorizedState()
        }
    }

    private fun setRegistrationState() {
        with(binding) {
            logInButton.setText(R.string.loginRegistrationText)
            loginTitle.setText(R.string.registrationTitle)
            registrationText.apply {
                setText(R.string.logIn)
                setOnClickListener {
                    viewModel.changeLoginScreenState(setLoginScreen = true)
                }
            }
        }
    }

    private fun setUnauthorizedState() {
        with(binding) {
            logInButton.setText(R.string.logIn)
            loginTitle.setText(R.string.logIn)
            registrationText.apply {
                setText(R.string.loginRegistrationText)
                setOnClickListener {
                    viewModel.changeLoginScreenState(setLoginScreen = false)
                }
            }
        }
    }

    private fun setUpLoginButton(){
        with(binding) {
            logInButton.setOnClickListener {
                viewModel.tryAuth(
                    name = loginNameBox.getTrimmedText(),
                    password = loginPasswordBox.getTrimmedText()
                )
            }
        }
    }

    private fun showToast(@StringRes value: Int) =
        Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
}