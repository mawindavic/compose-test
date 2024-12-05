package com.mawinda.domain.di

import com.mawinda.domain.AppRepository
import com.mawinda.domain.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Singleton
    @Binds
    abstract fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository
}