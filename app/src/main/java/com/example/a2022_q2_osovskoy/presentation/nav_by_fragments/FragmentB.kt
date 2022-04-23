package com.example.a2022_q2_osovskoy.presentation.nav_by_fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a2022_q2_osovskoy.R

class FragmentB : Fragment(R.layout.fragment_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonB).setOnClickListener {
            findNavController().navigate(R.id.action_fragmentB_to_fragmentC)
        }
    }
}