package com.ahtabbasi.roomlist.data.remote.datasources

import com.ahtabbasi.roomlist.data.remote.models.RoomsListDTO
import com.ahtabbasi.utils.network.NetworkResult

internal interface RoomRemoteDataSource {
    suspend fun getAllRooms(): NetworkResult<RoomsListDTO>
}