package com.ahtabbasi.roomlist.presentation.view

import com.ahtabbasi.roomlist.data.local.datasources.RoomLocalDataSourceImpl
import com.ahtabbasi.roomlist.data.remote.api.ApiService
import com.ahtabbasi.roomlist.data.remote.datasources.RoomRemoteDataSourceImpl
import com.ahtabbasi.roomlist.data.repositories.RoomRepositoryImpl
import com.ahtabbasi.roomlist.domain.usecases.GetAllRoomsUseCase
import com.ahtabbasi.roomlist.domain.usecases.GetAllRoomsUseCaseImpl
import com.ahtabbasi.roomlist.domain.usecases.SetBookedRoomUseCase
import com.ahtabbasi.roomlist.domain.usecases.SetBookedRoomUseCaseImpl

// Hilt also provides this feature but it only works with instrumentation tests
// Therefore, we need to use this custom class
internal class TestDependencyInjector(private val apiService: ApiService) {

    val getAllRoomsUseCase: GetAllRoomsUseCase
        get() = _getAllRoomsUseCase ?: throw UninitializedDependencyException()
    private var _getAllRoomsUseCase: GetAllRoomsUseCase? = null

    val setBookedRoomUseCase: SetBookedRoomUseCase
        get() = _setBookedRoomUseCase ?: throw UninitializedDependencyException()
    private var _setBookedRoomUseCase: SetBookedRoomUseCase? = null

    fun initialize(): TestDependencyInjector {
        val roomRepository = RoomRepositoryImpl(
            RoomLocalDataSourceImpl(),
            RoomRemoteDataSourceImpl(apiService)
        )
        _getAllRoomsUseCase = GetAllRoomsUseCaseImpl(roomRepository)
        _setBookedRoomUseCase = SetBookedRoomUseCaseImpl(roomRepository)
        return this
    }

    class UninitializedDependencyException : Exception()
}