package com.pl.sszczc.stepapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface StepsDao {

    @Insert
    fun insertAll(vararg users: Steps)

    @Delete
    fun delete(user: Steps)

    @Query("SELECT * FROM steps_table")
    fun getAll(): Flow<List<Steps>>

    @Update
    fun updateUsers(vararg users: Steps)
}