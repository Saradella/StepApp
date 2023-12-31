package com.pl.sszczc.stepapp.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class StepsRepository(private val StepsDao: StepsDao) {

    val allSteps: Flow<List<Steps>> = StepsDao.getAlphabetizedSteps()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(steps: Steps) {
        StepsDao.insert(steps)
    }
}
