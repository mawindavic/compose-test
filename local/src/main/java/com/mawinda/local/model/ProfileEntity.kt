package com.mawinda.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String,
)
