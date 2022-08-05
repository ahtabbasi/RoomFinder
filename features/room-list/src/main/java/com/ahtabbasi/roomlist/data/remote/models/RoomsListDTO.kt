package com.ahtabbasi.roomlist.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
internal data class RoomsListDTO(
    val rooms: List<RoomDTO>
)