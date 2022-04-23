package com.example.a2022_q2_osovskoy.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentBinding
import com.example.a2022_q2_osovskoy.presentation.viewmodel.ItemsViewModel
import com.example.a2022_q2_osovskoy.presentation.viewmodel.MainViewModelFactory

class ShiftFragment : Fragment(R.layout.fragment) {
    companion object {
        fun create(): ShiftFragment = ShiftFragment()
    }

    private val viewBinding by viewBinding(FragmentBinding::bind)

    private val viewModel: ItemsViewModel by viewModels {
        MainViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            recycler.apply {
                try {
                    addItemDecoration(DividerItemDecorator(ContextCompat.getDrawable(context,
                        R.drawable.devider)!!))
                } catch (e: NullPointerException) {
                    Toast.makeText(requireContext(), R.string.errorText, Toast.LENGTH_SHORT).show()
                }

            }.adapter = ItemsAdapter(
                { accept ->
                    showToast(accept, requireContext())
                },
                { decline ->
                    showToast(decline, requireContext())
                }, { name ->
                    showToast(name, requireContext())
                }
            )
            iconPlus.setOnClickListener {
                showToast(R.string.iconPlus, requireContext())
            }
        }

        viewModel.loadItems()

        viewModel.itemEvents.observe(this.viewLifecycleOwner) { itemEvent ->
            with(viewBinding) {
                when (itemEvent) {
                    is ItemsViewModel.ItemsEvent.Success -> {
                        (recycler.adapter as ItemsAdapter).items = itemEvent.result
                    }
                    ItemsViewModel.ItemsEvent.Error -> {
                        showToast(R.string.errorText, requireContext())
                    }
                }
            }
        }
    }

    private fun showToast(string: String, context: Context) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }

    private fun showToast(int: Int, context: Context) {
        Toast.makeText(context, int, Toast.LENGTH_SHORT).show()
    }
}