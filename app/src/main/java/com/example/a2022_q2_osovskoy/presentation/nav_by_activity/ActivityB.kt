package com.example.a2022_q2_osovskoy.presentation.nav_by_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.a2022_q2_osovskoy.R

class ActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        findViewById<Button>(R.id.activityButtonB).setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java))
        }
    }
}