package com.example.a2022_q2_osovskoy.ui.main_screen

import android.content.Context
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentMainBinding
import com.example.a2022_q2_osovskoy.domain.entity.ItemsState
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.viewmodel.ItemsViewModel
import com.example.a2022_q2_osovskoy.ui.second_screen.FragmentSecond
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ShiftFragment : Fragment(R.layout.fragment_main) {
    companion object {
        fun create(): ShiftFragment = ShiftFragment()
    }

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    lateinit var viewModel: ItemsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
        Log.d("ViewModelFragmentShift", "${ multiViewModelFactory.viewModelsClasses.map { it.hashCode().toString() } }")
        viewModel = ViewModelProvider(this, multiViewModelFactory)[ItemsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TransitionManager.beginDelayedTransition(viewBinding.mainScreen)
        viewBinding.mainScreen.isVisible = true

        with(viewBinding) {
            observeItemsState(this)
            setUpListener(this)
            listItemsRecycler.apply {
                ContextCompat.getDrawable(requireContext(),
                    R.drawable.devider)?.let { DividerItemDecorator(it) }
                    ?.let { addItemDecoration(it) }//todo()
            }.adapter = getAdapter()
        }
    }

    private fun setUpListener(binding: FragmentMainBinding) {
        binding.iconPlus.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                setCustomAnimations(
                    com.google.android.material.R.anim.abc_slide_in_bottom,
                    com.google.android.material.R.anim.abc_fade_out,
                    com.google.android.material.R.anim.abc_fade_in,
                    com.google.android.material.R.anim.abc_slide_out_bottom
                )
                replace(R.id.mainActivityFragmentContainer, FragmentSecond())
                addToBackStack(null)
                commit()
            }
        }
    }

    private fun observeItemsState(binding: FragmentMainBinding) {
        viewModel.itemsState.observe(this.viewLifecycleOwner) { itemsState ->
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

    private fun getAdapter(): ItemsAdapter = ItemsAdapter(
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