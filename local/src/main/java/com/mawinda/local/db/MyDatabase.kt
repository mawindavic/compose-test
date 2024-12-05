package com.mawinda.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mawinda.local.db.dao.ProfileDao
import com.mawinda.local.model.ProfileEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [ProfileEntity::class],
    version = RoomMigration.CURRENT_VERSION,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
}