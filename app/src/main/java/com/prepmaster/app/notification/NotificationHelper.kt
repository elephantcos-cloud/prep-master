package com.prepmaster.app.notification

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.prepmaster.app.MainActivity
import com.prepmaster.app.R

object NotificationHelper {
    const val CH_REMINDER = "reminder"
    const val CH_ACHIEVEMENT = "achievement"

    fun createChannels(ctx: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(NotificationChannel(
                CH_REMINDER,"Study Reminder", NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Daily preposition study reminders" })
            nm.createNotificationChannel(NotificationChannel(
                CH_ACHIEVEMENT,"Achievement", NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Badge and level notifications" })
        }
    }

    fun showReminder(ctx: Context, title: String, msg: String, id: Int = 2001) {
        val pi = PendingIntent.getActivity(ctx, 0,
            Intent(ctx, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            },
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notif = NotificationCompat.Builder(ctx, CH_REMINDER)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title).setContentText(msg)
            .setStyle(NotificationCompat.BigTextStyle().bigText(msg))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true).setContentIntent(pi).build()

        (ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(id, notif)
    }
}
