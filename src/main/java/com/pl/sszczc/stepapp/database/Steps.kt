package com.pl.sszczc.stepapp.database

import androidx.room.*
import java.util.*

@Entity(tableName = "steps_table")
data class Steps(
    @PrimaryKey(autoGenerate = true) val id: Int,
//    @ColumnInfo(name = "date") val date: Date?,
    @ColumnInfo(name = "steps") val steps: Int

)
