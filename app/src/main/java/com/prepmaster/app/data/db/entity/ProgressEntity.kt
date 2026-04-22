package com.prepmaster.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey val prepId: String,
    val categoryId: String,
    val learned   : Boolean = false,
    val learnedAt : String  = "",
    val xpEarned  : Int     = 0
)
