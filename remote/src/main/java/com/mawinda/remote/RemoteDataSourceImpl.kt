package com.mawinda.remote

import com.mawinda.remote.model.MedDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import timber.log.Timber
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val client: HttpClient) : RemoteDataSource {

    /**
     * Safe Api Call
     */
    private inline fun <reified T> safeApiCall(block: HttpClient.() -> T): Result<T> =
        try {
            val response = client.block()
            Result.success(response)
        } catch (ex: Exception) {
            Timber.e(ex)
            Result.failure(ex)
        }


    /**
     * Load Problems
     */
    override suspend fun loadMeds(): Result<List<MedDTO>> = safeApiCall {
        val response = get("https://run.mocky.io/v3/07f53008-73c9-41bd-af9c-284a8bc2bc32")
        return@safeApiCall RemoteJsonUtil.getItems(response.body())
    }
}