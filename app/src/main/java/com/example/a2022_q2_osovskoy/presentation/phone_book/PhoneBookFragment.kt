package com.example.a2022_q2_osovskoy.presentation.phone_book

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentPhoneBookBinding
import com.example.a2022_q2_osovskoy.presentation.add_person.FragmentPersonAdder
import com.example.a2022_q2_osovskoy.presentation.phone_book.view_model.ProviderMainViewModel
import com.example.a2022_q2_osovskoy.presentation.phone_book.view_model.ProviderViewModelFactory

class PhoneBookFragment : Fragment(R.layout.fragment_phone_book) {

    private val viewBinding by viewBinding(FragmentPhoneBookBinding::bind)

    private val viewModel: ProviderMainViewModel by viewModels {
        ProviderViewModelFactory(
            applicationContext = requireContext().applicationContext
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onPermissionResult(checkPermission())

        viewModel.personEvents.observe(viewLifecycleOwner)
        { personEvent ->
            with(viewBinding) {
                observeEvents(PersonEventViewer.Base(this), personEvent)
            }
        }

        with(viewBinding) {
            setAdapter(this)
            setUpListeners(this)
            personsRecyclerSwipe.apply {
                setOnRefreshListener {
                    viewModel.onPermissionResult(checkPermission())
                    isRefreshing = false
                }
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        { granted ->
            viewBinding.permissionView.setText(if (granted) R.string.granted else R.string.not_granted)
        }

    private fun checkPermission(): Boolean = if (PackageManager.PERMISSION_GRANTED == ContextCompat
            .checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            )
    ) {
        viewBinding.permissionView.setText(R.string.granted)
        true
    } else {
        requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        PackageManager.PERMISSION_GRANTED == ContextCompat
            .checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            )
    }

    private fun setUpListeners(binding: FragmentPhoneBookBinding) =
        with(binding) {
            requestPermissionButton.setOnClickListener {
                checkPermission()
            }
            addPersonButton.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, FragmentPersonAdder())
                    .addToBackStack(null).commit()
            }
            deleteAllButton.setOnClickListener {
                viewModel.deleteAllPersons()
            }
        }

    private fun setAdapter(binding: FragmentPhoneBookBinding) =
        binding.personsRecycler.apply {
            setDivider(this)
            adapter = PersonAdapter(
                update = { person ->
                    with(parentFragmentManager) {
                        setFragmentResult(UPDATE_SCREEN_KEY, bundleOf(UPDATE_VALUES to person))

                        beginTransaction()
                            .replace(R.id.container, FragmentPersonAdder())
                            .addToBackStack(null)
                            .commit()
                    }
                },
                delete = { person ->
                    viewModel.deletePerson(person)
                }
            )
        }

    private fun setDivider(recyclerView: RecyclerView) =
        try {
            recyclerView.addItemDecoration(DividerItemDecorator(ContextCompat.getDrawable(
                requireContext(),
                R.drawable.line)!!))
        } catch (e: NullPointerException) {
            Toast.makeText(requireContext(), R.string.errorText, Toast.LENGTH_SHORT).show()
        }

    private fun observeEvents(
        personEventViewer: PersonEventViewer,
        event: ProviderMainViewModel.PersonEvent,
    ) = personEventViewer.show(event)


    companion object {
        const val UPDATE_SCREEN_KEY = "update_screen_key"
        const val UPDATE_VALUES = "update_values"
    }
}