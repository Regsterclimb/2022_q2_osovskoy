package com.example.a2022_q2_osovskoy.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentMainBinding
import com.example.a2022_q2_osovskoy.presentation.viewmodel.MainViewModel
import com.example.a2022_q2_osovskoy.presentation.viewmodel.MultiViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyFragment : DaggerFragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, multiModuleFactory)[MainViewModel::class.java]
    }

    @Inject
    lateinit var multiModuleFactory: MultiViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            loadButton.setOnClickListener {
                viewModel.loadStrings()
            }
            viewModel.screenState.observe(viewLifecycleOwner) { newState ->
                renderState(newState, this)
            }
        }
    }

    private fun renderState(state: MainState, binding: FragmentMainBinding) {
        with(binding) {
            when (state) {
                is MainState.Loading -> {
                    remoteText.text = state.remoteString
                    localText.text = state.localString
                    showToast()
                }
                is MainState.Success -> {
                    remoteText.text = state.remoteString
                    localText.text = state.localString
                }
            }
        }
    }

    private fun showToast() {
        Toast.makeText(requireContext(), R.string.load, Toast.LENGTH_SHORT).show()
    }
}