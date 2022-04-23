package com.example.a2022_q2_osovskoy.presentation.nav_by_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.a2022_q2_osovskoy.R

class ActivityA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        findViewById<Button>(R.id.activityButtonA).setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java))
        }
    }
}