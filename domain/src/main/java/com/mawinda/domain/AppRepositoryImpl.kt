package com.mawinda.domain

import com.mawinda.local.LocalDataSource
import com.mawinda.remote.RemoteDataSource
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource
) : AppRepository {

    override suspend fun login(username: String, password: String): Result<Boolean> {
        // TODO: Store login data
        return Result.success(true)
    }

    override suspend fun greetUser(username: String): String =
        generateUserTimeBasedGreetings(username = username)
}