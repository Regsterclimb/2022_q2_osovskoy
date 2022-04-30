package com.example.a2022_q2_osovskoy.presentation.main_fragment

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.FragmentMainBinding
import com.example.a2022_q2_osovskoy.presentation.phone_book.PhoneBookFragment

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            dbSaveButton
                .setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container,
                            PhoneBookFragment::class.java,
                            bundleOf(LOADING_FLAG_KEY to DATA_BASE_FLAG))
                        .addToBackStack(null)
                        .commit()
                }
        }
    }

    companion object {
        const val LOADING_FLAG_KEY = "loadingFlag"
        const val FILE_FLAG = 0
        const val DATA_BASE_FLAG = 1
    }
}