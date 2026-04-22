package com.prepmaster.app

import android.app.Application
import com.prepmaster.app.notification.NotificationHelper
import com.prepmaster.app.worker.WorkScheduler

class PrepMasterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createChannels(this)
        WorkScheduler.schedule(this)
    }
}
