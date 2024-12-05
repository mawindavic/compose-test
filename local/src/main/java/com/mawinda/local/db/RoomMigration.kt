package com.mawinda.local.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object RoomMigration {
    /**
     * Each time the schema changes, [CURRENT_VERSION] has to be incremented.
     */
    const val CURRENT_VERSION = 1

    /**
     * Array of all migrations.
     */
    val MIGRATIONS = arrayOf<Migration>()


    /**
     * Create a [Migration] from [startVersion] to [endVersion].
     */
    private inline fun migration(
        startVersion: Int,
        endVersion: Int,
        crossinline block: SupportSQLiteDatabase.() -> Unit
    ): Migration = object : Migration(startVersion, endVersion) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.block()
        }
    }
}