package com.prepmaster.app.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

object WorkScheduler {
    fun schedule(ctx: Context) {
        WorkManager.getInstance(ctx).enqueueUniquePeriodicWork(
            "prep_reminder",
            ExistingPeriodicWorkPolicy.UPDATE,
            PeriodicWorkRequestBuilder<ReminderWorker>(3, TimeUnit.HOURS).build()
        )
    }
    fun cancel(ctx: Context) = WorkManager.getInstance(ctx).cancelUniqueWork("prep_reminder")
}
