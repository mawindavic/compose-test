package com.mawinda.local.di

import android.content.Context
import androidx.room.Room
import com.mawinda.local.LocalDataSource
import com.mawinda.local.LocalDataSourceImpl
import com.mawinda.local.db.MyDatabase
import com.mawinda.local.db.RoomMigration
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalBinder {
    @Singleton
    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, "my_database")
            .addMigrations(*RoomMigration.MIGRATIONS).fallbackToDestructiveMigration().build()


    @Provides
    @Singleton
    fun provideProfileDao(database: MyDatabase) = database.profileDao()

}