package com.pl.sszczc.stepapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Steps::class], version = 1, exportSchema = false) // arrayOf(Steps::class), version = 1, exportSchema = false)
public abstract class StepsRoomDatabase : RoomDatabase() {

    abstract fun StepsDao(): StepsDao

    companion object {

        @Volatile
        private var INSTANCE: StepsRoomDatabase? = null

        fun getDatabase(context: Context): StepsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): StepsRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                StepsRoomDatabase::class.java,
                "steps_table"
            )
                .build()
        }
    }


//    abstract fun StepsDao(): StepsDao
//
//    companion object {
//
//        @Volatile
//        private var INSTANCE: StepsRoomDatabase? = null
//
//        fun getDatabase(context: Context): StepsRoomDatabase {
//
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    StepsRoomDatabase::class.java,
//                    "steps_database"
//                ).build()
//                INSTANCE = instance
//
//                instance
//            }
//        }
//    }
//
//    @TypeConverters(Converters::class)
//    abstract class AppDatabase : StepsRoomDatabase() {}

}

