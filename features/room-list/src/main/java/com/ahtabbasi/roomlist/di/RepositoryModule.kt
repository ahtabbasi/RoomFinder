package com.ahtabbasi.roomlist.di

import com.ahtabbasi.roomlist.data.local.datasources.RoomLocalDataSource
import com.ahtabbasi.roomlist.data.remote.datasources.RoomRemoteDataSource
import com.ahtabbasi.roomlist.data.repositories.RoomRepositoryImpl
import com.ahtabbasi.roomlist.domain.repositories.RoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    internal fun provideRoomRepository(
        localDataSource: RoomLocalDataSource,
        remoteDataSource: RoomRemoteDataSource,
    ): RoomRepository {
        return RoomRepositoryImpl(localDataSource, remoteDataSource)
    }

}