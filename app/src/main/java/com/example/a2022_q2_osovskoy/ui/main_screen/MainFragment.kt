package com.example.a2022_q2_osovskoy.ui.main_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentMainBinding
import com.example.a2022_q2_osovskoy.ui.add_person_screen.AddPersonFragment
import com.example.a2022_q2_osovskoy.ui.base_phone_book.PhoneBookFragment
import com.example.a2022_q2_osovskoy.ui.file_phone_book.FilePhoneBookFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            setUpButton(dbSaveButton, PhoneBookFragment(), DATA_BASE_FLAG)
            setUpButton(fileSaveButton, FilePhoneBookFragment(), FILE_FLAG)
        }
    }

    private fun setUpButton(button: Button, fragment: Fragment, flag : Int) = button.setOnClickListener {
        with(parentFragmentManager) {
            setFragmentResult(REQUEST_FRAGMENT_FLAG_KEY, bundleOf(FRAGMENT_FLAG_KEY to flag))
            beginTransaction().replace(R.id.container, AddPersonFragment())
                .addToBackStack(null)
                .commit()
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        const val REQUEST_FRAGMENT_FLAG_KEY = "Request fragment key"
        private const val FRAGMENT_FLAG_KEY = "fragment key"
        const val DATA_BASE_FLAG = 0
        const val FILE_FLAG = 1
    }
}