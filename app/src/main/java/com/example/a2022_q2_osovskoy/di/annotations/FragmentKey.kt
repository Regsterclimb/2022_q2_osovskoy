package com.example.a2022_q2_osovskoy.di.annotations

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class FragmentKey(val value: KClass<out Fragment>)
