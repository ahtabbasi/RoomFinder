package com.ahtabbasi.roomlist.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
internal data class RoomDTO(
    val name: String,
    val spots: Int,
    val thumbnail: String
)