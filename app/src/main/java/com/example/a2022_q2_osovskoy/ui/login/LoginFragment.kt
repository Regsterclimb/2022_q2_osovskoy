package com.example.a2022_q2_osovskoy.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.LoginFragmentBinding
import com.example.a2022_q2_osovskoy.presentation.login.LoginModelState
import com.example.a2022_q2_osovskoy.presentation.login.LoginViewModel
import com.example.a2022_q2_osovskoy.ui.MultiViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment(R.layout.login_fragment) {

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[LoginViewModel::class.java]
    }

    private val viewBinding by viewBinding(LoginFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            observeLoginState(this)
            registrationButton.setOnClickListener {
                viewModel.startRegistration(BaseUser(loginEditName.text.toString(),
                    loginEditPassword.text.toString()))
            }
            logInButton.setOnClickListener {
                viewModel.startLogin(BaseUser(loginEditName.text.toString(),
                    loginEditPassword.text.toString()))
            }
        }
    }

    private fun observeLoginState(binding: LoginFragmentBinding) {
        viewModel.loginState.observe(viewLifecycleOwner) {
            when (it) {
                is LoginModelState.Succeed -> {
                    Toast.makeText(requireContext(), "SUccess", Toast.LENGTH_SHORT).show()
                }
                is LoginModelState.Error -> {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}