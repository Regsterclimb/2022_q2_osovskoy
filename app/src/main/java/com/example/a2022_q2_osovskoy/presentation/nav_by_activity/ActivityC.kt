package com.example.a2022_q2_osovskoy.presentation.nav_by_activity

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.a2022_q2_osovskoy.R

class ActivityC : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        findViewById<Button>(R.id.activityButtonC).setOnClickListener {
            startActivity(Intent(this, ActivityA::class.java).addFlags(FLAG_ACTIVITY_CLEAR_TOP))
        }
    }
}