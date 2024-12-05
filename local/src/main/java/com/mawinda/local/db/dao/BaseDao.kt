package com.mawinda.local.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update

interface BaseDao<T> {

    /**
     * Insert an object in the database.
     */
    @Throws
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    @Throws
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg obj: T)

    @Throws
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: List<T>)

    /**
     * Update an object from the database.
     */
    @Throws
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: T)

    @Throws
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg obj: T)

    @Throws
    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: List<T>)

    /**
     * Delete an object from the database
     */
    @Transaction
    @Delete
    suspend fun delete(obj: T)

    @Transaction
    @Delete
    suspend fun delete(vararg obj: T)

    @Transaction
    @Delete
    suspend fun delete(obj: List<T>)
}