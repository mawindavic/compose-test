package com.mawinda.domain

interface AppRepository {
    suspend fun login(username: String, password: String): Result<Boolean>
    suspend fun greetUser(username: String): String
}