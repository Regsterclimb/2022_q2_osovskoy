package com.example.a2022_q2_osovskoy.presentation.add_person

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentAddPersonBinding
import com.example.a2022_q2_osovskoy.domain.model.Person
import com.example.a2022_q2_osovskoy.presentation.add_person.view_model.PersonAdderViewModel
import com.example.a2022_q2_osovskoy.presentation.phone_book.PhoneBookFragment
import com.example.a2022_q2_osovskoy.presentation.phone_book.view_model.ProviderViewModelFactory

class FragmentPersonAdder : Fragment(R.layout.fragment_add_person) {

    private val viewBinding by viewBinding(FragmentAddPersonBinding::bind)

    private val viewModel: PersonAdderViewModel by viewModels {
        ProviderViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(PhoneBookFragment.UPDATE_SCREEN_KEY) { _, bundle ->
            updateEvent(bundle)
        }

        with(viewBinding) {
            acceptButton.setOnClickListener {
                showToastIfCreateEmpty(editTextName.text.toString(),
                    editTextPhone.text.toString())
            }
            backFromAddPerson.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun updateEvent(bundle: Bundle) {
        val person = bundle.getSerializable(PhoneBookFragment.UPDATE_VALUES) as Person
        with(viewBinding) {
            editTextName.setText(person.name)
            editTextPhone.setText(person.number)
            acceptButton.setOnClickListener {
                showToastIfUpdateEmpty(editTextName.text.toString(),
                    editTextPhone.text.toString(),
                    person)
            }
        }
    }

    private fun showToastIfUpdateEmpty(name: String, phone: String, person: Person) {
        if (name.isEmpty() && phone.isEmpty()) {
            showToast()
        } else {
            viewModel.updatePerson(Person(person.id, name, phone))
            parentFragmentManager.popBackStack()
        }
    }

    private fun showToastIfCreateEmpty(name: String, phone: String) {
        if (name.isEmpty() && phone.isEmpty()) {
            showToast()
        } else {
            viewModel.createPerson(
                Person(
                    id = 0,
                    name = name,
                    number = phone)
            )
            parentFragmentManager.popBackStack()
        }
    }

    private fun showToast() {
        Toast.makeText(requireContext(), R.string.addNameOrphone, Toast.LENGTH_SHORT).show()
    }
}