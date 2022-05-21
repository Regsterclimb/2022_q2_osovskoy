package com.example.a2022_q2_osovskoy.presentation

import android.os.Bundle
import com.example.a2022_q2_osovskoy.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.mainContainer, MyFragment())
                .commit()
        }
    }
}