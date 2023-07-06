package com.pl.sszczc.stepapp.database

import androidx.room.*

@Entity(tableName = "steps_table")
data class Steps(@PrimaryKey @ColumnInfo(name = "steps") val steps: String)
