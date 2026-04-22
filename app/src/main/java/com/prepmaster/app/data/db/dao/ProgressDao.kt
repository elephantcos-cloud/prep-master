package com.prepmaster.app.data.db.dao

import androidx.room.*
import com.prepmaster.app.data.db.entity.ProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Query("SELECT * FROM progress")
    fun getAll(): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress WHERE prepId=:id")
    suspend fun get(id: String): ProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(p: ProgressEntity)

    @Query("SELECT COUNT(*) FROM progress WHERE learned=1 AND categoryId=:catId")
    suspend fun learnedCount(catId: String): Int
}
