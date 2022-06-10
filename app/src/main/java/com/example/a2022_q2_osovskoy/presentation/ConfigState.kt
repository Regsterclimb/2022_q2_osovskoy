package com.example.a2022_q2_osovskoy.presentation

sealed class ConfigState {
    object Unauthorized : ConfigState()
    object Authorized: ConfigState()
    object Uninstructed : ConfigState()
    object Base: ConfigState()
}