package com.example.a2022_q2_osovskoy.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.presentation.MultiViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.SupportViewModel
import com.example.a2022_q2_osovskoy.ui.auth.AuthFragment
import com.example.a2022_q2_osovskoy.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var multiViewModelFactory: MultiViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiViewModelFactory)[SupportViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.appConfigState.observe(this) { isUsedLoggedIn ->
            if (savedInstanceState == null) {
                openFragmentIfLogged(isUsedLoggedIn)
            }
        }
    }

    private fun openFragmentIfLogged(isUsedLoggedIn: Boolean) {
        supportFragmentManager.beginTransaction()
            .add(R.id.activityContainer, if (isUsedLoggedIn) MainFragment() else AuthFragment())
            .commit()
    }
}