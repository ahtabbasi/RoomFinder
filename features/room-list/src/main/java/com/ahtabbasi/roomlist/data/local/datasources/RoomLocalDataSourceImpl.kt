package com.ahtabbasi.roomlist.data.local.datasources

import com.ahtabbasi.domain.models.Room
import javax.inject.Inject

//TODO: Store in Database
internal class RoomLocalDataSourceImpl @Inject constructor() : RoomLocalDataSource {

    private var roomsInDatabase: List<Room>? = null

    override suspend fun saveRooms(rooms: List<Room>) {
        roomsInDatabase = rooms
    }

    override suspend fun getAllRooms(): List<Room>? {
        return roomsInDatabase
    }

    override suspend fun bookRoom(room: Room) {
        val roomInDB = roomsInDatabase?.firstOrNull { it.name == room.name }
        if (roomInDB != null) {
            roomsInDatabase = roomsInDatabase?.map {
                it.copy(
                    isBooked = it.name == roomInDB.name
                )
            }
        }
    }
}