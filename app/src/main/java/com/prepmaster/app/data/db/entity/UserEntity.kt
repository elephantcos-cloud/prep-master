package com.prepmaster.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: Int = 1,
    val name           : String = "শিক্ষার্থী",
    val xp             : Int    = 0,
    val level          : Int    = 1,
    val streak         : Int    = 0,
    val lastStudyDate  : String = "",
    val totalStudied   : Int    = 0,
    val quizCorrect    : Int    = 0,
    val quizTotal      : Int    = 0,
    val practiceCorrect: Int    = 0,
    val practiceTotal  : Int    = 0,
    val flashcardsViewed:Int   = 0,
    val studyGoalMins  : Int    = 20,
    val todayMins      : Int    = 0,
    val notifEnabled   : Boolean= true,
    val joinDate       : String = ""
)
