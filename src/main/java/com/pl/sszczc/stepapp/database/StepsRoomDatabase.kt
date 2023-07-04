package com.pl.sszczc.stepapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Steps::class], version = 1)
abstract class StepsRoomDatabase : RoomDatabase() {

    abstract fun StepsDao(): StepsDao

    companion object {
        @Volatile
        private var INSTANCE: StepsRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): StepsRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StepsRoomDatabase::class.java,
                    "steps_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(StepsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }

        private class StepsDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.StepsDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(stepsDao: StepsDao) {
            stepsDao.deleteAll()

            var steps = Steps("ble")
            stepsDao.insert(steps)
            steps = Steps("bli")
            stepsDao.insert(steps)
        }
    }
}

