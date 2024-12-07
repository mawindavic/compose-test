package com.mawinda.remote

import com.mawinda.remote.model.MedDTO

interface RemoteDataSource {

    suspend fun loadMeds(): Result<List<MedDTO>>
}