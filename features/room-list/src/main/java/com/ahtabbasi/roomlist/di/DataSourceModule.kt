package com.ahtabbasi.roomlist.di

import com.ahtabbasi.roomlist.data.local.datasources.RoomLocalDataSource
import com.ahtabbasi.roomlist.data.local.datasources.RoomLocalDataSourceImpl
import com.ahtabbasi.roomlist.data.remote.api.ApiService
import com.ahtabbasi.roomlist.data.remote.datasources.RoomRemoteDataSource
import com.ahtabbasi.roomlist.data.remote.datasources.RoomRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {

    @Provides
    @ActivityRetainedScoped
    internal fun provideRoomLocalDataSource(): RoomLocalDataSource {
        return RoomLocalDataSourceImpl()
    }

    @Provides
    @ActivityRetainedScoped
    internal fun provideRoomRemoteDataSource(
        apiService: ApiService,
    ): RoomRemoteDataSource {
        return RoomRemoteDataSourceImpl(apiService)
    }

}