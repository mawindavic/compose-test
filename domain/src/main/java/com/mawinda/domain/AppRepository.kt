package com.mawinda.domain

interface AppRepository {
    suspend fun login(username: String, password: String)
    suspend fun greetUser(username: String): String
}