package com.mawinda.remote.di

import com.mawinda.remote.RemoteDataSource
import com.mawinda.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteBinder {
    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}

@Module
@InstallIn(SingletonComponent::class)
object RemoteProviders {


    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideNetworkClint(
        loggingInterceptor: HttpLoggingInterceptor
    ) = HttpClient(OkHttp) {

        engine {
            config {
                followRedirects(true)
                followSslRedirects(true)
            }

            addInterceptor(loggingInterceptor)
        }

    }

}