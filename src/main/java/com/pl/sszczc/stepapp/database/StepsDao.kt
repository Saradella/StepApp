package com.pl.sszczc.stepapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StepsDao {

    @Query("SELECT * FROM steps_table ORDER BY steps ASC")

    @Insert()
    suspend fun insert(word: Database)

    @Query("DELETE FROM steps_table")
    suspend fun deleteAll()
}
