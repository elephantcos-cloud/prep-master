package com.prepmaster.app.data.db.dao

import androidx.room.*
import com.prepmaster.app.data.db.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id=1")
    fun get(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(u: UserEntity)

    @Update
    suspend fun update(u: UserEntity)

    @Query("UPDATE user SET xp=xp+:xp, level=:level WHERE id=1")
    suspend fun addXp(xp: Int, level: Int)

    @Query("UPDATE user SET streak=:streak, lastStudyDate=:date WHERE id=1")
    suspend fun updateStreak(streak: Int, date: String)

    @Query("UPDATE user SET totalStudied=totalStudied+1 WHERE id=1")
    suspend fun incStudied()

    @Query("UPDATE user SET quizCorrect=quizCorrect+:c, quizTotal=quizTotal+:t WHERE id=1")
    suspend fun addQuiz(c: Int, t: Int)

    @Query("UPDATE user SET practiceCorrect=practiceCorrect+:c, practiceTotal=practiceTotal+:t WHERE id=1")
    suspend fun addPractice(c: Int, t: Int)

    @Query("UPDATE user SET flashcardsViewed=flashcardsViewed+1 WHERE id=1")
    suspend fun incFlashcard()
}
