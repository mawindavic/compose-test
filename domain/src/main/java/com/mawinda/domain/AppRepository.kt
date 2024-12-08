package com.mawinda.domain

import com.mawinda.domain.model.Med
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    val username: Flow<String?>
    val greetings: Flow<String>
    suspend fun login(username: String, password: String): Result<Boolean>
    suspend fun logout(): Result<Boolean>
    suspend fun loadMeds(): Result<List<Med>>
}