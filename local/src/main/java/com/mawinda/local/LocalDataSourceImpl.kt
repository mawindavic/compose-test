package com.mawinda.local

import com.mawinda.local.db.dao.ProfileDao
import com.mawinda.local.model.ProfileEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val profileDao: ProfileDao) :
    LocalDataSource {

    override val loggedUser = profileDao.loggedUser()

    /**
     * Logs in a user with the provided username and password.
     */
    override suspend fun login(username: String, password: String): Result<Boolean> {
        val profile = ProfileEntity(username = username, password = password)
        profileDao.apply {
            deleteAll()
            insert(profile)
        }
        return Result.success(true)
    }
}