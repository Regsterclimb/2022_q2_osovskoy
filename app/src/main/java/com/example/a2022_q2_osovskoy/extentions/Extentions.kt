package com.example.a2022_q2_osovskoy.extentions

import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.a2022_q2_osovskoy.App
import com.example.a2022_q2_osovskoy.R
import com.example.a2022_q2_osovskoy.ui.MainActivity

fun Int.toMyTimerFormat(): String = String.format("%02d:%02d", this % 3600 / 60, this % 60)

fun provideNotificationBuilder(
    context: Context,
    pendingIntent: PendingIntent,
): NotificationCompat.Builder = NotificationCompat.Builder(context, App.CHANNEL_ID).apply {
    setContentIntent(pendingIntent)
    setSmallIcon(R.drawable.notification_icon)
    priority = NotificationCompat.PRIORITY_HIGH
    setCategory(NotificationCompat.CATEGORY_MESSAGE)
}

fun providePendingIntent(context: Context, intent: Intent): PendingIntent =
    TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(intent)
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

fun provideIntent(context: Context): Intent = Intent(context,
    MainActivity::class.java).setFlags(
    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
)
