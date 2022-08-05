package com.ahtabbasi.roomlist.data.mappers

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.data.local.models.BookedRoom
import com.ahtabbasi.roomlist.data.remote.models.RoomDTO


internal object RoomMapper {

    fun toDomainModel(remote: RoomDTO, isBooked: Boolean) = Room(
        name = remote.name,
        spots = remote.spots,
        photo = remote.thumbnail,
        isBooked = isBooked
    )
}