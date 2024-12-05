package com.mawinda.local

import com.mawinda.local.db.MyDatabase
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val database: MyDatabase) : LocalDataSource {
}