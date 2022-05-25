package com.example.a2022_q2_osovskoy.ui.main_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentShiftBinding
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.viewmodel.ItemsState
import com.example.a2022_q2_osovskoy.presentation.viewmodel.ItemsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ShiftFragment : DaggerFragment(R.layout.fragment_shift) {

    private val viewBinding by viewBinding(FragmentShiftBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[ItemsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            observeItemsState(this)
            setUpItemsRecycler(this)
            iconPlus.isVisible = false
        }
    }

    private fun setUpItemsRecycler(binding: FragmentShiftBinding) {
        binding.listItemsRecycler.apply {
            ContextCompat.getDrawable(requireContext(), R.drawable.devider)?.let { drawable ->
                DividerItemDecorator(drawable)
            }?.let { divideItemDecorator ->
                addItemDecoration(divideItemDecorator)
            }
        }.adapter = getItemsAdapter()
    }

    private fun observeItemsState(binding: FragmentShiftBinding) {
        viewModel.itemsState.observe(viewLifecycleOwner) { itemsState ->
            when (itemsState) {
                is ItemsState.Success -> {
                    (binding.listItemsRecycler.adapter as ItemsAdapter).items = itemsState.result
                }
                ItemsState.Error -> {
                    showToast(R.string.errorText, requireContext())
                }
            }
        }
    }

    private fun getItemsAdapter(): ItemsAdapter = ItemsAdapter(
        onAcceptClick = { accept ->
            showToast(accept, requireContext())
        },
        onDeclineClick = { decline ->
            showToast(decline, requireContext())
        },
        onArrowClickAction = { name ->
            showToast(name, requireContext())
        }
    )

    private fun showToast(string: String, context: Context) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(@StringRes value: Int, context: Context) {
        Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
    }
}