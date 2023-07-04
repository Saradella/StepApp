package com.pl.sszczc.stepapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*
@Dao
interface StepsDao {

    @Query("SELECT * FROM steps_table ORDER BY steps ASC")
    fun getAlphabetizedSteps(): Flow<List<Steps>>
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(steps: Steps)
    @Query("DELETE FROM steps_table")
    fun deleteAll()
}
