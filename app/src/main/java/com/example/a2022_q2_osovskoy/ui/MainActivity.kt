package com.example.a2022_q2_osovskoy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.ui.main_screen.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, MainFragment())
                .commit()
        }
    }
}