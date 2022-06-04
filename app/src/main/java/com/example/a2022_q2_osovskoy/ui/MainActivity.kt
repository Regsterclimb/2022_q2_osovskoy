package com.example.a2022_q2_osovskoy.ui

import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.data.datasource.worker.NotificationWorker
import com.example.a2022_q2_osovskoy.databinding.ActivityMainBinding
import com.example.a2022_q2_osovskoy.presentation.MainViewModel
import com.example.a2022_q2_osovskoy.presentation.MainViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.TimerState
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var multiModelFactory: MainViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, multiModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NotificationManagerCompat.from(applicationContext)
            .cancel(NotificationWorker.NOTIFICATION_ID)

        with(viewBinding) {
            observeTimerState(this)
            shutDawnButton.setOnClickListener {
                viewModel.shutDawnTimer()
            }
        }
    }

    private fun observeTimerState(binding: ActivityMainBinding) {
        viewModel.timerState.observe(this) { timerState ->
            when (timerState) {
                is TimerState.Stopped -> initTimerStopEvent(binding, timerState.stoppedTime)

                is TimerState.Working -> initTimerWorkingEvent(binding, timerState.workingTime)

                TimerState.ShutDowned -> initTimerShutDawnEvent(binding)
            }
        }
    }

    private fun initTimerStopEvent(binding: ActivityMainBinding, time: String) {
        with(binding) {
            timerValue.text = time
            setStartButton(startAndStopButton)
        }
    }

    private fun initTimerWorkingEvent(binding: ActivityMainBinding, workingTime: String) {
        with(binding) {
            timerValue.text = workingTime
            startAndStopButton.apply {
                setOnClickListener {
                    viewModel.stopTimer(workingTime)
                }
                setText(R.string.stopButtonText)
            }
        }
    }

    private fun initTimerShutDawnEvent(binding: ActivityMainBinding) {
        with(binding) {
            timerValue.setText(R.string.timerInitialValue)
            setStartButton(startAndStopButton)
        }
    }

    private fun setStartButton(button: Button) {
        button.apply {
            setOnClickListener {
                viewModel.startTimer()
            }
            setText(R.string.startButtonText)
        }
    }

    private fun pushNotificationWithLastTime() {
        viewModel.timerValue.observe(this) { time ->
            viewModel.pushNotification(time)
        }
    }

    override fun finish() {
        pushNotificationWithLastTime()
        viewModel.apply {
            shutDawnTimer()
            destroyTimer()
        }
        super.finish()
    }

    companion object {
        const val DATA_ID = "data_id"
    }
}