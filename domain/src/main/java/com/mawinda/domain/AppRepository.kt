package com.mawinda.domain

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    val username: Flow<String>
    val greetings: Flow<String>
    suspend fun login(username: String, password: String): Result<Boolean>
}