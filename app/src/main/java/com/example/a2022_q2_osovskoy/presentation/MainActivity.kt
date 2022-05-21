package com.example.a2022_q2_osovskoy.presentation

import android.os.Bundle
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.ui.main_screen.ShiftFragment
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityFragmentContainer, ShiftFragment())
                .commit()
        }
    }
}