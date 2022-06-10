package com.example.a2022_q2_osovskoy.utils.navigation

import androidx.fragment.app.Fragment

fun Fragment.navigate(navCommand: NavCommand) {
    (requireActivity() as? NavigationProvider)?.launch(navCommand)
}