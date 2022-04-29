package com.example.a2022_q2_osovskoy.presentation.currency_list

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.CurrenciesFragmentBinding
import com.example.a2022_q2_osovskoy.presentation.MainModelFactory
import com.example.a2022_q2_osovskoy.presentation.currency_detail.CurrencyFragment
import com.example.a2022_q2_osovskoy.presentation.currency_list.view_model.CurrenciesViewModel


class MainFragment : Fragment(R.layout.currencies_fragment) {

    private val viewBinding by viewBinding(CurrenciesFragmentBinding::bind)

    private val viewModel: CurrenciesViewModel by viewModels {
        MainModelFactory(applicationContext = requireContext().applicationContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            currenciesRecycler.apply {
                try {
                    addItemDecoration(DividerItemDecorator(ContextCompat.getDrawable(requireContext(),
                        R.drawable.line)!!))
                } catch (e: NullPointerException) {
                    showToast(requireContext(), R.string.currenciesLoadingError)
                }
            }.adapter = CurrencyListAdapter { myCurrency ->
                openCurrencyChanger(myCurrency.id)
            }
            observeCurrencyEvents(this)
        }
    }

    private fun openCurrencyChanger(currencyId: String) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, CurrencyFragment.newInstance(currencyId))
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeCurrencyEvents(binding: CurrenciesFragmentBinding) {
        viewModel.currenciesEvent.observe(this.viewLifecycleOwner)
        { resultEvent ->
            with(binding) {
                when (resultEvent) {
                    is CurrenciesViewModel.CurrenciesState.Success -> {
                        (currenciesRecycler.adapter as CurrencyListAdapter).apply {
                            submitList(resultEvent.data)
                            notifyDataSetChanged()
                        }
                        currenciesLoader.isVisible = false
                        currenciesRecycler.isVisible = true
                    }
                    CurrenciesViewModel.CurrenciesState.Error -> {
                        currenciesLoader.isVisible = false
                        currenciesRecycler.isVisible = false
                        showToast(requireContext(), R.string.currenciesLoadingError)
                    }
                    CurrenciesViewModel.CurrenciesState.Loading -> {
                        currenciesLoader.isVisible = true
                        currenciesRecycler.isVisible = false
                    }
                    CurrenciesViewModel.CurrenciesState.IsEmpty -> {
                        currenciesLoader.isVisible = false
                        currenciesRecycler.isVisible = false
                        showToast(requireContext(), R.string.resultLoadingIsEmpty)
                    }
                }
            }
        }
    }

    private fun showToast(context: Context, @StringRes message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(): MainFragment = MainFragment()
    }
}