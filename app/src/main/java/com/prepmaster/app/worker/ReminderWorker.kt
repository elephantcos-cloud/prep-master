package com.prepmaster.app.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.prepmaster.app.data.repository.AppRepository
import com.prepmaster.app.notification.NotificationHelper
import com.prepmaster.app.notification.ReminderMessages
import kotlinx.coroutines.flow.first
import java.util.Calendar

class ReminderWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        val repo = AppRepository.get(applicationContext)
        val user = repo.userFlow().first() ?: return Result.success()
        if (!user.notifEnabled) return Result.success()
        val (title, msg) = ReminderMessages.get(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
        NotificationHelper.showReminder(applicationContext, title, msg)
        return Result.success()
    }
}
