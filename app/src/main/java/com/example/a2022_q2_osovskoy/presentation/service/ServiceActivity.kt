package com.example.a2022_q2_osovskoy.presentation.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ActivityServiceBinding


class ServiceActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityServiceBinding::bind)

    private var service: TimerService? = null

    private var serviceConnection: ServiceConnection? = null

    private fun bindToService() {
        if (serviceConnection != null) {
            return
        }
        serviceConnection = serviceConnection()

        bindService(getServiceIntent(), serviceConnection!!, Context.BIND_AUTO_CREATE)
    }

    private fun unbindFromService() {
        serviceConnection?.let { connection ->
            unbindService(connection)

            serviceConnection = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        with(viewBinding) {
            bindService.setOnClickListener {
                bindToService()
                stop.isEnabled = false
                unBindService.isEnabled = true
                countDawnTimer.isVisible = true
                start.isEnabled = true
            }
            unBindService.setOnClickListener {
                unbindFromService()
                stop.isEnabled = true
                start.isEnabled = false
                bindService.isEnabled = false
            }
            start.setOnClickListener {
                startService(getServiceIntent())
                stop.isEnabled = true
                unBindService.isEnabled = false
                bindService.isEnabled = true
            }
            stop.setOnClickListener {
                stopService(getServiceIntent())
                countDawnTimer.isVisible = false
                start.isEnabled = true
            }
        }
    }

    private fun getServiceIntent() = Intent(this, TimerService::class.java)

    private fun serviceConnection(): ServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            service = (binder as? TimerService.TimerBinder)?.getService()
            service?.observeTime()?.observe(this@ServiceActivity) { time ->
                viewBinding.countDawnTimer.text = time ?: ""
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            service = null
        }
    }

    override fun onDestroy() {
        service = null
        serviceConnection = null
        super.onDestroy()
    }
}