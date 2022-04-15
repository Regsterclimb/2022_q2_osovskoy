package com.example.a2022_q2_osovskoy

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DispatchersRep {

    fun getIO(): CoroutineDispatcher = Dispatchers.IO
    fun getDefault(): CoroutineDispatcher = Dispatchers.Default
    fun getMain(): CoroutineDispatcher = Dispatchers.Main
}