package com.example.a2022_q2_osovskoy.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ActivityMainBinding
import com.example.a2022_q2_osovskoy.presentation.MainViewModel
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var multiModuleFactory: MultiViewModelFactory

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, multiModuleFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewBinding) {
            animalRecycler.adapter = AnimalAdapter()
        }

        viewModel.result.observe(this) {
            (viewBinding.animalRecycler.adapter as AnimalAdapter).submitList(it)
        }
    }
}