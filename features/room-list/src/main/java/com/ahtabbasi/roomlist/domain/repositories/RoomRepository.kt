package com.ahtabbasi.roomlist.domain.repositories

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.domain.models.GetAllRoomsResult

internal interface RoomRepository {
    suspend fun getAllRooms(): GetAllRoomsResult
    suspend fun bookRoom(room: Room)
}

