package com.mawinda.domain

interface AppRepository {
    suspend fun login(username: String, password: String)
}