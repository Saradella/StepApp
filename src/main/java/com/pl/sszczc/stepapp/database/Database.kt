package com.pl.sszczc.stepapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "steps_table")
data class Database(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "steps") val steps: Int

)