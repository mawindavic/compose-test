package com.mawinda.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.mawinda.local.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao : BaseDao<ProfileEntity> {

    @Query("SELECT username FROM profile LIMIT 1")
    fun loggedUser(): Flow<String>

    @Query("DELETE FROM profile")
    suspend fun deleteAll()

}