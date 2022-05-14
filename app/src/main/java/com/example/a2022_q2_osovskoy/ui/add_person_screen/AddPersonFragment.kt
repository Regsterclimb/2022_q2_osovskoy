package com.example.a2022_q2_osovskoy.ui.add_person_screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentAddPersonBinding
import com.example.a2022_q2_osovskoy.domain.entity.Person
import com.example.a2022_q2_osovskoy.domain.entity.add_person.FieldsState
import com.example.a2022_q2_osovskoy.presentation.MainViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.add_person.AddPersonViewModel
import com.example.a2022_q2_osovskoy.ui.base_phone_book.PhoneBookFragment


class AddPersonFragment : Fragment(R.layout.fragment_add_person) {

    private val viewBinding by viewBinding(FragmentAddPersonBinding::bind)

    private val viewModel: AddPersonViewModel by viewModels {
        MainViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(PhoneBookFragment.UPDATE_SCREEN_KEY) { _, bundle ->
            viewModel.setUpdate(updateEvent(bundle))
        }
    }

    private fun updateEvent(bundle: Bundle): Person {
        val person = bundle.getSerializable(PhoneBookFragment.UPDATE_VALUES) as Person
        with(viewBinding) {
            editTextName.setText(person.name)
            editTextPhone.setText(person.number)
        }
        return Person(person.id, person.name, person.number)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            backFromAddPerson.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            viewModel.fieldsState.observe(viewLifecycleOwner) { state ->
                acceptButton.setOnClickListener {
                    observeEvents(state)
                }
            }
            setWatcher(this)
        }
    }

    private fun setWatcher(binding: FragmentAddPersonBinding) = with(binding) {
        editTextName.afterTextChanged { name ->
            viewModel.watchFields(
                name,
                editTextPhone.text.toString()
            )
        }
        editTextPhone.afterTextChanged { phone ->
            viewModel.watchFields(
                editTextName.text.toString(),
                phone
            )
        }
    }

    private fun observeEvents(state: FieldsState) = when (state) {
        is FieldsState.Success -> observeClick(state.name, state.number)
        is FieldsState.Empty -> showToast(R.string.addNameOrphone)
        is FieldsState.Error -> showToast(R.string.errorLength)
    }

    private fun observeClick(name: String, phone: String) =
        viewModel.isUpdate.observe(viewLifecycleOwner) { personUpdate ->
            clickAccept(personUpdate.isUpdate, personUpdate.person, name, phone)
        }

    private fun clickAccept(isUpdate: Boolean, person: Person, name: String, phone: String) {
        val myPerson = Person(person.id, name, phone)
        with(viewModel) {
            if (isUpdate) {
                updatePerson(myPerson)
            } else {
                createPerson(myPerson)
            }
        }
        parentFragmentManager.popBackStack()
    }

    private fun showToast(@StringRes value: Int) =
        Toast.makeText(requireContext(), value, Toast.LENGTH_SHORT).show()

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) =
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        }
        )
}