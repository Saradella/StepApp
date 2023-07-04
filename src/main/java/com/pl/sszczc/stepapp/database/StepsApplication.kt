package com.pl.sszczc.stepapp.database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class StepsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { StepsRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StepsRepository(database.StepsDao()) }
}