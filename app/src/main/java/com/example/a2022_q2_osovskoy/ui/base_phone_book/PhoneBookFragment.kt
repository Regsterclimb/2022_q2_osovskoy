package com.example.a2022_q2_osovskoy.ui.base_phone_book

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentPhoneBookBinding
import com.example.a2022_q2_osovskoy.domain.entity.base_phone_book.FirstEvent
import com.example.a2022_q2_osovskoy.domain.entity.base_phone_book.PersonEvent
import com.example.a2022_q2_osovskoy.presentation.MainViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.base_phone_book.BaseViewModel
import com.example.a2022_q2_osovskoy.ui.PersonAdapter
import com.example.a2022_q2_osovskoy.ui.add_person_screen.AddPersonFragment
import com.example.a2022_q2_osovskoy.ui.core.DividerItemDecorator
import com.example.a2022_q2_osovskoy.ui.core.PersonEventDisplay
import com.example.a2022_q2_osovskoy.ui.main_screen.MainFragment

class PhoneBookFragment : Fragment(R.layout.fragment_phone_book) {

    private val viewBinding by viewBinding(FragmentPhoneBookBinding::bind)

    private val viewModel: BaseViewModel by viewModels {
        MainViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(MainFragment.REQUEST_FRAGMENT_FLAG_KEY) { _, bundle ->
            viewModel.setPhoneBookState(bundle.getInt(MainFragment.REQUEST_FRAGMENT_FLAG_KEY))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()

        with(viewBinding) {
            setUpPersonsRecycler(binding = this)
            setUpListeners(binding = this)
            setObservers(this)
            personsRecyclerSwipe.apply {
                setOnRefreshListener {
                    checkPermission()
                    isRefreshing = false
                }
            }
        }
    }

    private fun setUpPersonsRecycler(binding: FragmentPhoneBookBinding) = with(binding) {
        personsRecycler.apply {
            setDivider(this)
            adapter = PersonAdapter(
                //todo()
                updatePerson = { person ->
                    with(parentFragmentManager) {
                        setFragmentResult(UPDATE_SCREEN_KEY, bundleOf(UPDATE_VALUES to person))
                        beginTransaction().replace(R.id.container, AddPersonFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                },
                deletePerson = { person ->
                    viewModel.deletePerson(person)
                }
            )
        }
    }

    private fun setUpListeners(binding: FragmentPhoneBookBinding) = with(binding) {
        addPersonButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, AddPersonFragment())
                .addToBackStack(null).commit()
        }
        deleteAllButton.setOnClickListener {
            viewModel.deleteAllPersons()
        }
        phoneBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun setObservers(binding: FragmentPhoneBookBinding) = with(viewModel) {
        firstEvents.observe(viewLifecycleOwner) {
            observeFirstEvent(it)
        }
        isGranted.observe(viewLifecycleOwner) { granted ->
            binding.permissionView.setText(
                if (granted) R.string.granted else R.string.not_granted
            )
        }
        personEvents.observe(viewLifecycleOwner) { personEvent ->
            observeEvents(binding, personEvent, PersonEventDisplay(binding))
        }
    }

    private fun observeFirstEvent(firstEvent: FirstEvent) = when (firstEvent) {
        is FirstEvent.Loading -> {
            with(viewBinding) {
                progressBar.isVisible = true
                errorText.isVisible = false
                personsRecycler.isVisible = false
            }
        }
        is FirstEvent.Success -> showToast(R.string.success)
        is FirstEvent.Error -> showToast(R.string.error)
    }

    private fun observeEvents(
        binding: FragmentPhoneBookBinding,
        personEvent: PersonEvent,
        personEventDisplay: PersonEventDisplay,
    ) = with(personEventDisplay) {
        when (personEvent) {
            is PersonEvent.Success -> {
                (binding.personsRecycler.adapter as PersonAdapter).apply {
                    submitList(personEvent.result)
                }
                showSuccess()
            }
            is PersonEvent.Loading -> showLoading()
            PersonEvent.Empty -> showEmpty()
            PersonEvent.Error -> showError()
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        { granted ->
            if (granted) {
                viewModel.setGranted(true)
                viewModel.loadFirstTime()
            } else {
                viewModel.setGranted(false)
                viewModel.loadPersons()
            }
        }

    private fun checkPermission() {
        val num = PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS)
        if (num) {
            viewModel.setGranted(true)
            viewModel.loadFirstTime()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun setDivider(recyclerView: RecyclerView) = try {
        recyclerView.addItemDecoration(DividerItemDecorator(ContextCompat.getDrawable(
            requireContext(),
            R.drawable.line)!!))
    } catch (e: NullPointerException) {
        showToast(R.string.errorText)
    }

    private fun showToast(@StringRes value: Int) =
        Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()

    companion object {
        const val UPDATE_SCREEN_KEY = "update_screen_key"
        const val UPDATE_VALUES = "update_values"
    }
}