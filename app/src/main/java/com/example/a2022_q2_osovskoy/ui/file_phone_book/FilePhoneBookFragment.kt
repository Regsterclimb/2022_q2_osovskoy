package com.example.a2022_q2_osovskoy.ui.file_phone_book

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
import com.example.a2022_q2_osovskoy.domain.entity.file_phone_book.FileFirstEvent
import com.example.a2022_q2_osovskoy.domain.entity.file_phone_book.FilePersonEvent
import com.example.a2022_q2_osovskoy.presentation.MainViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.file_phone_book.FileBookViewModel
import com.example.a2022_q2_osovskoy.ui.PersonAdapter
import com.example.a2022_q2_osovskoy.ui.core.DividerItemDecorator
import com.example.a2022_q2_osovskoy.ui.core.PersonEventDisplay

class FilePhoneBookFragment : Fragment(R.layout.fragment_phone_book) {

    private val viewModel: FileBookViewModel by viewModels {
        MainViewModelFactory(applicationContext = requireContext().applicationContext)
    }
    private val viewBinding by viewBinding(FragmentPhoneBookBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()

        with(viewBinding) {
            setObservers(this)
            setUpPersonsRecycler(this)
            setUpListeners(this)
            personsRecyclerSwipe.apply {
                setOnRefreshListener {
                    checkPermission()
                    isRefreshing = false
                }
            }
            addPersonButton.isVisible = false
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission())
        { granted ->
            with(viewModel) {
                if (granted) {
                    setGranted(true)
                    loadFirstTime()
                } else {
                    setGranted(false)
                    loadPersons()
                }
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

    private fun setUpListeners(binding: FragmentPhoneBookBinding) = with(binding) {
        deleteAllButton.setOnClickListener {
            viewModel.deleteAllPersons()
        }
        phoneBackButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeFirstEvent(firstEvent: FileFirstEvent) = when (firstEvent) {
        is FileFirstEvent.Loading -> {
            with(viewBinding) {
                progressBar.isVisible = true
                errorText.isVisible = false
                personsRecycler.isVisible = false
            }
        }
        is FileFirstEvent.Success -> showToast(R.string.success)
        is FileFirstEvent.Error -> showToast(R.string.error)
    }

    private fun observeEvents(
        binding: FragmentPhoneBookBinding,
        personEvent: FilePersonEvent,
        personEventDisplay: PersonEventDisplay,
    ) = with(personEventDisplay) {
        when (personEvent) {
            is FilePersonEvent.Success -> {
                (binding.personsRecycler.adapter as PersonAdapter).apply {
                    submitList(personEvent.result)
                }
                showSuccess()
            }
            is FilePersonEvent.Loading -> showLoading()
            FilePersonEvent.Empty -> showEmpty()
            FilePersonEvent.Error -> showError()
        }
    }

    private fun setUpPersonsRecycler(binding: FragmentPhoneBookBinding) =
        binding.personsRecycler.apply {
            setDivider(this)
            adapter = PersonAdapter(
                updatePerson = {
                    showToast(R.string.menuChange)
                },
                deletePerson = {
                    showToast(R.string.menuDelete)
                }
            )
        }

    private fun setDivider(recyclerView: RecyclerView) = try {
        recyclerView.addItemDecoration(DividerItemDecorator(ContextCompat.getDrawable(
            requireContext(),
            R.drawable.line)!!))
    } catch (e: NullPointerException) {
        showToast(R.string.errorText)
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

    private fun showToast(stringId: Int) =
        Toast.makeText(requireContext(), stringId, Toast.LENGTH_SHORT).show()
}
