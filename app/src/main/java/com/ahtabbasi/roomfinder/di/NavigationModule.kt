package com.ahtabbasi.roomfinder.di

import com.ahtabbasi.roomfinder.navigation.AppNavigator
import com.ahtabbasi.roomlist.presentation.navigation.RoomListNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    @Singleton
    internal fun provideNavigator(): RoomListNavigator {
        return AppNavigator()
    }
}