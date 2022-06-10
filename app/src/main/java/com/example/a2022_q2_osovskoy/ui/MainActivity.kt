package com.example.a2022_q2_osovskoy.ui

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommand
import com.example.a2022_q2_osovskoy.utils.navigation.NavCommands
import com.example.a2022_q2_osovskoy.utils.navigation.NavigationProvider
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), NavigationProvider {

    private val navController: NavController
        get() = findNavController(R.id.activityContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun launch(navCommand: NavCommand) {
        if (navCommand.target is NavCommands.DeepLink) {
            val target = navCommand.target
            openDeepLink(
                url = target.url,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
        }
    }

    //todo()
    private fun openDeepLink(url: Uri, isModal: Boolean, isSingleTop: Boolean) {
        val navOptions = if (isModal) {
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_up_enter)
                .setExitAnim(R.anim.slide_out_up_exit)
                .setPopEnterAnim(R.anim.slide_in_up_exit)
                .setPopExitAnim(R.anim.slide_out_up_exit)
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.app_nav_graph else -1, inclusive = isSingleTop)
                .build()
        } else {
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_left)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.app_nav_graph else -1, inclusive = isSingleTop)
                .build()
        }
        navController.navigate(url, navOptions)
    }
}