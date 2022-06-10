package com.example.a2022_q2_osovskoy.ui.registration

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.RegistrationFragmentBinding
import com.example.a2022_q2_osovskoy.extentions.getTrimmedText
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

        setUpRegistrationButton()
        setAuthText()

        viewModel.regState.observe(viewLifecycleOwner, ::handleRegState)
    }

    //todo() обнулить текст при смене экрана + ресурсы
    private fun handleRegState(state: RegState) {
        when (state) {
            is RegState.Success -> {
                navigateToAuth()
                Toast.makeText(requireContext(), "Registeration success", Toast.LENGTH_SHORT).show()
            }
            is RegState.Error -> {
                Toast.makeText(requireContext(), "Registeration Error", Toast.LENGTH_SHORT).show()
            }
            is RegState.InputError.Name -> showToast(R.string.inputNameEmpty)
            is RegState.InputError.Password -> showToast(R.string.inputPasswordEmpty)
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

    private fun setAuthText() {
        binding.authText.setOnClickListener {
            navigate(NavCommand(
                NavCommands.DeepLink(
                    Uri.parse(NavDestination.DEEP_AUTH),
                    isModal = true,
                    isSingleTop = true))
            )
        }
    }

    private fun showToast(@StringRes value: Int) =
        Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()
}