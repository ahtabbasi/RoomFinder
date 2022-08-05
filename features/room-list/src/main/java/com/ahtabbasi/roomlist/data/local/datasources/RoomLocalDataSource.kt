package com.ahtabbasi.roomlist.data.local.datasources

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.data.local.models.BookedRoom
import com.ahtabbasi.roomlist.data.remote.models.RoomsListDTO
import com.ahtabbasi.utils.network.NetworkResult

internal interface RoomLocalDataSource {
    suspend fun saveRooms(rooms: List<Room>)
    suspend fun getAllRooms(): List<Room>?
    suspend fun bookRoom(room: Room)
}