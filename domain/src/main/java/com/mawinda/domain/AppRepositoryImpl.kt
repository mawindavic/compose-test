package com.mawinda.domain

import com.mawinda.local.LocalDataSource
import com.mawinda.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource
) : AppRepository {


    override val username: Flow<String> = localDataSource.loggedUser


    /**
     * Generates a user-specific time-based greetings message.
     */
    override val greetings: Flow<String> = flow {
        emit(generateUserTimeBasedGreetings())
    }

    /**
     * Logs in a user with the provided username and password.
     */
    override suspend fun login(username: String, password: String): Result<Boolean> =
        localDataSource.login(username = username, password = password)


}