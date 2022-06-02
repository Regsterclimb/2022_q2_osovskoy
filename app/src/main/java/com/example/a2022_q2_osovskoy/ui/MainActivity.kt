package com.example.a2022_q2_osovskoy.ui

import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.databinding.ActivityMainBinding
import com.example.a2022_q2_osovskoy.presentation.MainViewModel
import com.example.a2022_q2_osovskoy.presentation.MainViewModelFactory
import com.example.a2022_q2_osovskoy.presentation.TimerState
import dagger.android.support.DaggerAppCompatActivity
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {


    private val viewBinding by viewBinding(ActivityMainBinding::bind)

    @Inject
    lateinit var multiModelFactory: MainViewModelFactory

    @Inject
    lateinit var mainWorkerFactory: MainWorkerFactory

    private val viewModel by lazy {
        ViewModelProvider(this,
            multiModelFactory)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                is TimerState.Working -> initTimerWorkingEvent(binding, timerState.workingTime)

                is TimerState.Stopped -> {
                    initTimerStopEvent(binding, timerState.stoppedTime)
                }

                TimerState.ShutDowned -> initTimerShutDawnEvent(binding)
                //todo()
                TimerState.Started -> {}
            }
        }
    }

    private fun initTimerStopEvent(binding: ActivityMainBinding, time: String) {
        with(binding) {
            timerValue.text = time
            setStartAndStopButtonListener(
                this,
                onClick = { viewModel.startTimer() },
                R.string.startButtonText)
        }
    }

    private fun initTimerWorkingEvent(binding: ActivityMainBinding, workingTime: String) {
        with(binding) {
            timerValue.text = workingTime
            setStartAndStopButtonListener(this,
                onClick = { viewModel.stopTimer(workingTime) },
                R.string.stopButtonText)
        }
    }

    private fun initTimerShutDawnEvent(binding: ActivityMainBinding) {
        with(binding) {
            setStartAndStopButtonListener(
                this,
                onClick = { viewModel.startTimer() },
                R.string.startButtonText)
            timerValue.setText(R.string.timerInitialValue)
        }
    }

    private fun setStartAndStopButtonListener(
        binding: ActivityMainBinding,
        onClick: () -> Unit,
        @StringRes strRes: Int,
    ) {
        binding.startAndStopButton.apply {
            setText(strRes)
            setOnClickListener {
                onClick()
            }
        }
    }

    private fun createOneTimeRequest(string: String): OneTimeWorkRequest {
        val inputData: Data = Data.Builder().putString(DATA_ID, string).build()
        return OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(inputData)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build();
    }

    private fun sendNotificationWithLastTime() {
        viewModel.timerValue.observe(this) {
            WorkManager.getInstance(this.applicationContext).enqueue(createOneTimeRequest(it))
        }
    }

    override fun finish() {
        Log.d("MyWorker", "FINISHED")
        sendNotificationWithLastTime()
        viewModel.shutDawnTimer()
        super.finish()
    }

    companion object {
        const val DATA_ID = "data_id"
    }
}