package com.example.a2022_q2_osovskoy

import android.app.Application
import com.example.a2022_q2_osovskoy.di.DaggerAppComponent


class App :Application() {

    val appComponent = DaggerAppComponent.factory().create()
}