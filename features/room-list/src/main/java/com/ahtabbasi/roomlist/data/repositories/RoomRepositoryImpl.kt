package com.ahtabbasi.roomlist.data.repositories

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.data.local.datasources.RoomLocalDataSource
import com.ahtabbasi.roomlist.data.mappers.RoomMapper
import com.ahtabbasi.roomlist.data.remote.datasources.RoomRemoteDataSource
import com.ahtabbasi.roomlist.domain.models.GetAllRoomsResult
import com.ahtabbasi.roomlist.domain.repositories.RoomRepository
import com.ahtabbasi.utils.network.NetworkResult
import javax.inject.Inject

internal class RoomRepositoryImpl @Inject constructor(
    private val localDataSource: RoomLocalDataSource,
    private val remoteDataSource: RoomRemoteDataSource,
) : RoomRepository {

    override suspend fun getAllRooms(): GetAllRoomsResult {
        val localData = localDataSource.getAllRooms()

        return if (localData != null) {
            GetAllRoomsResult.Success(localData)
        } else when (
            val rooms = remoteDataSource.getAllRooms()) {
            is NetworkResult.Failure -> GetAllRoomsResult.Error(rooms.error)
            is NetworkResult.Success -> GetAllRoomsResult.Success(
                rooms.data.rooms.map {
                    RoomMapper.toDomainModel(it, false)
                }
            )
        }
    }

    override suspend fun bookRoom(room: Room) {
        localDataSource.bookRoom(room)
    }

}

