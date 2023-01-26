package com.popularpenguin.nyc.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.popularpenguin.nyc.data.SatScores
import com.popularpenguin.nyc.data.School

@Database(
    entities = [School::class, SatScores::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AppDao
}