package com.example.a2022_q2_osovskoy.presentation.currency_detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.CurrencyFragmentBinding
import com.example.a2022_q2_osovskoy.domain.edit_text.EditTextWatcher
import com.example.a2022_q2_osovskoy.domain.model.MyCurrency
import com.example.a2022_q2_osovskoy.presentation.MainModelFactory
import com.example.a2022_q2_osovskoy.presentation.currency_detail.view_model.CurrencyViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CurrencyFragment : Fragment(R.layout.currency_fragment) {

    private val viewModel: CurrencyViewModel by viewModels {
        MainModelFactory(applicationContext = requireContext().applicationContext)
    }
    private val viewBinding by viewBinding(CurrencyFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireArguments().getString(ARG_CURRENCY_ID)?.let { viewModel.getMyCurrency(it) }

        with(viewBinding) {
            observeMyCurrencyEvent(this, ViewState.Currency(this))
            observeResultEvent(this, ViewState.Result(this))
            currencyBackButton
                .setOnClickListener {
                    parentFragmentManager.popBackStack()
                }
        }
    }

    private fun bindUi(myCurrency: MyCurrency, binding: CurrencyFragmentBinding) {
        with(binding) {
            with(myCurrency) {
                resultCurrencyNumber.text = charCode
                currencyNameValue.text = name
                currencyNumCodeValue.text = numCode
                currencyActualValue.text = value.toString()
                currencyPreviousValue.text = previous.toString()
                doOnTextChange(currencyEditText, currencyTextInput)

                currencyChangeButton.setOnClickListener {
                    viewModel.changeCurrency(currencyEditText.text.toString(), value)
                }
                with(charCode) {
                    resultCurrencyNumber.text = this
                    currencyCharCodeValue.text = this
                }
            }
        }
    }

    private fun doOnTextChange(editText: TextInputEditText, textInputLayout: TextInputLayout) {
        editText.doOnTextChanged { text, _, _, _ ->
            if (EditTextWatcher.countDigitAfterDot(text.toString())) {
                textInputLayout.helperText = getString(R.string.inputFormat)
            } else {
                textInputLayout.helperText = getString(R.string.inputFormat)
            }
        }
    }

    private fun observeMyCurrencyEvent(
        binding: CurrencyFragmentBinding,
        viewStateFragment: ViewState,
    ) {
        viewModel.myCurrencyEvent.observe(viewLifecycleOwner) { resultEvent ->
            with(viewStateFragment) {
                when (resultEvent) {
                    is CurrencyViewModel.CurrencyResult.Loading -> viewStateFragment.showLoading()
                    is CurrencyViewModel.CurrencyResult.Success -> {
                        bindUi(resultEvent.data, binding)
                        showSuccess()
                    }
                    CurrencyViewModel.CurrencyResult.Error -> {
                        showError()
                        Toast.makeText(requireContext(),
                            R.string.currencyLoadingError,
                            Toast.LENGTH_SHORT).show()
                    }
                    CurrencyViewModel.CurrencyResult.IsEmpty -> {
                        showError()
                        Toast.makeText(requireContext(),
                            R.string.resultLoadingIsEmpty,
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun observeResultEvent(
        binding: CurrencyFragmentBinding,
        viewStateResult: ViewState,
    ) {
        viewModel.resultEvent.observe(viewLifecycleOwner) { changeEvent ->
            with(binding.result) {
                isVisible = true
                when (changeEvent) {
                    is CurrencyViewModel.ChangeResult.Success -> {
                        viewStateResult.showSuccess()
                        text = changeEvent.data
                    }
                    CurrencyViewModel.ChangeResult.Error -> {
                        viewStateResult.showError()
                        setText(R.string.resultLoadingError)
                    }
                    CurrencyViewModel.ChangeResult.Loading -> {
                        viewStateResult.showLoading()
                    }
                    CurrencyViewModel.ChangeResult.IsEmpty -> {
                        viewStateResult.showError()
                        setText(R.string.resultLoadingIsEmpty)
                    }
                }
            }
        }
    }

    companion object {
        private const val ARG_CURRENCY_ID = "ARG_CURRENCY_ID"

        fun newInstance(currencyId: String): CurrencyFragment {
            val fragment = CurrencyFragment()
            fragment.arguments = bundleOf("ARG_CURRENCY_ID" to currencyId)
            return fragment
        }
    }
}
