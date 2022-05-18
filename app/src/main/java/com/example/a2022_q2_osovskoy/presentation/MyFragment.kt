package com.example.a2022_q2_osovskoy.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentMainBinding
import com.example.a2022_q2_osovskoy.presentation.view_model.MainViewModel
import com.example.a2022_q2_osovskoy.presentation.view_model.MultiViewModelFactory
import javax.inject.Inject

class MyFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var multiModuleFactory: MultiViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as App).appComponent.getMainActivityComponent().create()
            .getMyFragmentComponent().create()
            .inject(this)
        viewModel = ViewModelProvider(this, multiModuleFactory)[MainViewModel::class.java]
        Log.d("MainViewModel", multiModuleFactory.viewModelsClasses.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            loadButton.setOnClickListener {
                viewModel.loadStrings()
            }
            viewModel.state.observe(viewLifecycleOwner) { newState ->
                renderState(newState, this)
            }
        }
    }

    //todo()
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
        Toast.makeText(requireContext(), "loading", Toast.LENGTH_SHORT).show()
    }
}