package com.example.a2022_q2_osovskoy.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.ui.main_screen.ShiftFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var injector :DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityFragmentContainer, ShiftFragment.create())
                .commit()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = injector
}