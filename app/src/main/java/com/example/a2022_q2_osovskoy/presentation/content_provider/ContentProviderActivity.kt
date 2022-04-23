package com.example.a2022_q2_osovskoy.presentation.content_provider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.a2022_q2_osovskoy.R


class ContentProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, ContentProviderFragment())
                .commit()
        }
    }
}