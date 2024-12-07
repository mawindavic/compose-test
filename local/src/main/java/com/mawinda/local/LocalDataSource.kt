package com.mawinda.local

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    val loggedUser: Flow<String>
    suspend fun login(username: String, password: String): Result<Boolean>

}