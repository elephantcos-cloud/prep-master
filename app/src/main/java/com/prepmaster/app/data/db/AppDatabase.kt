package com.prepmaster.app.data.db

import android.content.Context
import androidx.room.*
import com.prepmaster.app.data.db.dao.*
import com.prepmaster.app.data.db.entity.*

@Database(
    entities = [UserEntity::class, ProgressEntity::class, BadgeEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao()    : UserDao
    abstract fun progressDao(): ProgressDao
    abstract fun badgeDao()   : BadgeDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun get(ctx: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(ctx.applicationContext, AppDatabase::class.java, "prepmaster.db")
                .build().also { INSTANCE = it }
        }
    }
}
