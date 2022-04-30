package com.example.a2022_q2_osovskoy.presentation.main_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentMainBinding
import com.example.a2022_q2_osovskoy.presentation.file_phone_book.FilePhoneBookFragment
import com.example.a2022_q2_osovskoy.presentation.phone_book.PhoneBookFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            with(parentFragmentManager.beginTransaction()) {
                dbSaveButton
                    .setOnClickListener {
                        this.replace(R.id.container,
                            PhoneBookFragment())
                            .addToBackStack(null)
                            .commit()
                    }
                fileSaveButton.setOnClickListener {
                    this.replace(R.id.container,
                        FilePhoneBookFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        }
    }
}