package com.ahtabbasi.roomlist.testutils

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.data.remote.models.RoomDTO
import com.ahtabbasi.roomlist.data.remote.models.RoomsListDTO

internal object FakeData {

    val roomsDTO = RoomsListDTO(
        rooms = listOf(
            RoomDTO(name = "Fake Room 1", spots = 5, thumbnail = "Fake thumbnail 1"),
            RoomDTO(name = "Fake Room 2", spots = 7, thumbnail = "Fake thumbnail 2"),
            RoomDTO(name = "Fake Room 3", spots = 9, thumbnail = "Fake thumbnail 3")
        )
    )

    val rooms = listOf(
        Room(name = "Fake Room 1", spots = 5, photo = "Fake thumbnail 1", isBooked = false),
        Room(name = "Fake Room 2", spots = 7, photo = "Fake thumbnail 2", isBooked = false),
        Room(name = "Fake Room 3", spots = 9, photo = "Fake thumbnail 3", isBooked = false),
    )

}