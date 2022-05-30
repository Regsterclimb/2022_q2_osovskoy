package com.example.a2022_q2_osovskoy

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a2022_q2_osovskoy.data.datasource.TimerImpl
import com.example.a2022_q2_osovskoy.data.repository.MainRepositoryImpl
import com.example.a2022_q2_osovskoy.domain.usecase.StartTimerUseCase
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private val timerExecutor = Executors.newFixedThreadPool(4)

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRepo = MainRepositoryImpl(TimerImpl { time ->
            handler.post {
                val myTime = String.format("%02d:%02d", time % 3600 / 60, time % 60)
                findViewById<TextView>(R.id.timerValue).text = myTime
            }
        })

        val startTimerUseCase = StartTimerUseCase(mainRepo)
        startTimerUseCase()

    }
}