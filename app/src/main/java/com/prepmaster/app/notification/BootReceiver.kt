package com.prepmaster.app.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.prepmaster.app.worker.WorkScheduler

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED)
            WorkScheduler.schedule(ctx)
    }
}
