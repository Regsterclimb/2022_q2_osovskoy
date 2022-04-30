package com.example.a2022_q2_osovskoy.presentation.file_phone_book

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentPhoneBookBinding
import com.example.a2022_q2_osovskoy.presentation.ProviderViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.phone_book.DividerItemDecorator
import com.example.a2022_q2_osovskoy.presentation.phone_book.PersonAdapter

class FilePhoneBookFragment : Fragment(R.layout.fragment_phone_book) {

    private val viewModel: FileBookViewModel by viewModels {
        ProviderViewModelFactory(applicationContext = requireContext().applicationContext)
    }
    private val viewBinding by viewBinding(FragmentPhoneBookBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onPermissionResult(checkPermission())

        viewModel.personEvents.observe(viewLifecycleOwner) { personEvent ->
            with(viewBinding) {
                observeEvents(FilePersonEventViewer.Base(this), personEvent)
            }
        }
        with(viewBinding) {
            bindAdapter(this)
            personsRecyclerSwipe.apply {
                setOnRefreshListener {
                    viewModel.onPermissionResult(checkPermission())
                    isRefreshing = false
                }
            }
            setUpListeners(this)

            viewModel.personEvents.observe(viewLifecycleOwner) { personEvent ->
                observeEvents(FilePersonEventViewer.Base(this), personEvent)
            }
            addPersonButton.isVisible = false
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission())
    { granted ->
        viewBinding.permissionView.setText(if (granted) R.string.granted else R.string.not_granted)
    }

    private fun checkPermission(): Boolean =
        if (checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            checkSelfPermission() == PackageManager.PERMISSION_GRANTED
        }

    private fun setDivider(recyclerView: RecyclerView) =
        try {
            recyclerView.addItemDecoration(DividerItemDecorator(ContextCompat.getDrawable(
                requireContext(),
                R.drawable.line)!!))
        } catch (e: NullPointerException) {
            showToast(R.string.errorText)
        }

    private fun checkSelfPermission(): Int =
        ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS)

    private fun observeEvents(
        filePersonEventViewer: FilePersonEventViewer,
        event: FileBookViewModel.PersonEvent,
    ) = filePersonEventViewer.show(event)

    private fun showToast(stringId: Int) {
        Toast.makeText(requireContext(), stringId, Toast.LENGTH_SHORT).show()
    }

    private fun bindAdapter(binding: FragmentPhoneBookBinding) {
        binding.personsRecycler.apply {
            setDivider(this)
            adapter = PersonAdapter(
                update = {
                    showToast(R.string.menuChange)
                },
                delete = {
                    showToast(R.string.menuDelete)
                }
            )
        }
    }

    private fun setUpListeners(binding: FragmentPhoneBookBinding) {
        with(binding) {
            deleteAllButton.setOnClickListener {
                viewModel.deleteAllPersons()
            }
            phoneBackButton.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            requestPermissionButton.setOnClickListener {
                checkPermission()
            }
        }
    }
}