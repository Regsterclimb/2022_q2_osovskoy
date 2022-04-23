package com.example.a2022_q2_osovskoy.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ActivityMainBinding
import com.example.a2022_q2_osovskoy.presentation.content_provider.ContentProviderActivity
import com.example.a2022_q2_osovskoy.presentation.nav_by_activity.ActivityA
import com.example.a2022_q2_osovskoy.presentation.nav_by_fragments.FragmentsActivity
import com.example.a2022_q2_osovskoy.presentation.service.ServiceActivity

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewBinding) {
            toNavByActivity.setOnClickListener {
                initActivity(this@MainActivity, ActivityA::class.java)
            }
            toNavByFragments.setOnClickListener {
                initActivity(this@MainActivity, FragmentsActivity::class.java)
            }
            toContentProvider.setOnClickListener {
                initActivity(this@MainActivity, ContentProviderActivity::class.java)
            }
            toService.setOnClickListener {
                initActivity(this@MainActivity, ServiceActivity::class.java)
            }
        }
    }

    private fun initActivity(packageContext: Context?, cls: Class<*>?) {
        startActivity(Intent(packageContext, cls))
    }
}