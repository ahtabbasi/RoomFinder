package com.ahtabbasi.roomlist.di

import com.ahtabbasi.roomlist.domain.repositories.RoomRepository
import com.ahtabbasi.roomlist.domain.usecases.GetAllRoomsUseCase
import com.ahtabbasi.roomlist.domain.usecases.GetAllRoomsUseCaseImpl
import com.ahtabbasi.roomlist.domain.usecases.SetBookedRoomUseCase
import com.ahtabbasi.roomlist.domain.usecases.SetBookedRoomUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    internal fun provideGetAllRoomsUseCase(
        repository: RoomRepository,
    ): GetAllRoomsUseCase {
        return GetAllRoomsUseCaseImpl(repository)
    }

    @Provides
    internal fun provideSetBookedRoomUseCase(
        repository: RoomRepository,
    ): SetBookedRoomUseCase {
        return SetBookedRoomUseCaseImpl(repository)
    }
}