package com.example.a2022_q2_osovskoy.presentation.content_provider

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentContentProviderBinding
import com.example.a2022_q2_osovskoy.presentation.content_provider.view_model.ProviderMainViewModel
import com.example.a2022_q2_osovskoy.presentation.content_provider.view_model.ProviderViewModelFactory

class ContentProviderFragment : Fragment(R.layout.fragment_content_provider) {

    private val viewBinding by viewBinding(FragmentContentProviderBinding::bind)

    private val viewModel: ProviderMainViewModel by viewModels {
        ProviderViewModelFactory(
            applicationContext = requireContext().applicationContext
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            requestPermissionButton.setOnClickListener {
                checkPermission()
            }
            requestDataButton.setOnClickListener {
                viewModel.loadPersons()
            }
            recycler.adapter = PersonAdapter()
        }

        viewModel.personEvents.observe(this.viewLifecycleOwner) { personEvent ->
            with(viewBinding) {
                when (personEvent) {
                    is ProviderMainViewModel.PersonEvent.Success -> {
                        (recycler.adapter as PersonAdapter).apply {
                            submitList(personEvent.result)
                            notifyDataSetChanged()
                        }
                        progressBar.isVisible = false
                        recycler.isVisible = true
                        requestDataButton.isEnabled = true
                    }
                    is ProviderMainViewModel.PersonEvent.Loading -> {
                        requestDataButton.isEnabled = false
                        progressBar.isVisible = true
                        errorText.isVisible = false
                        recycler.isVisible = false
                    }
                    ProviderMainViewModel.PersonEvent.Empty -> {
                        errorText.apply {
                            setText(R.string.emptyText)
                            isVisible = true
                        }
                        requestDataButton.isEnabled = true
                        progressBar.isVisible = false
                        recycler.isVisible = false
                    }
                    ProviderMainViewModel.PersonEvent.Error -> {
                        errorText.apply {
                            setText(R.string.errorText)
                            isVisible = true
                        }
                        requestDataButton.isEnabled = true
                        progressBar.isVisible = false
                        recycler.isVisible = false
                    }
                }
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { granted ->
        viewBinding.permissionView.setText(
            if (granted) R.string.granted else R.string.not_granted
        )
    }

    private fun checkPermission() {
        return if (PackageManager.PERMISSION_GRANTED == ContextCompat
                .checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_CONTACTS
                )
        ) {
            viewBinding.permissionView.setText(R.string.granted)
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }
}