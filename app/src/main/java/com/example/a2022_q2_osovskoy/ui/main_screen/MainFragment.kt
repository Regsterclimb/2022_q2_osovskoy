package com.example.a2022_q2_osovskoy.ui.main_screen

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentMainBinding
import com.example.a2022_q2_osovskoy.ui.base_phone_book.PhoneBookFragment
import com.example.a2022_q2_osovskoy.ui.file_phone_book.FilePhoneBookFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            setUpButton(dbSaveButton, PhoneBookFragment())
            setUpButton(fileSaveButton, FilePhoneBookFragment())
        }
    }

    private fun setUpButton(button: Button, fragment: Fragment) = button.setOnClickListener {
        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}