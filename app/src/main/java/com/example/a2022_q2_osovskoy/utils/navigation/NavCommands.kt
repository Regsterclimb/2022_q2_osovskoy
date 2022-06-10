package com.example.a2022_q2_osovskoy.utils.navigation

import android.net.Uri

sealed class NavCommands {
    data class DeepLink(val url: Uri, val isModal: Boolean, val isSingleTop: Boolean = false) :
        NavCommands()
}