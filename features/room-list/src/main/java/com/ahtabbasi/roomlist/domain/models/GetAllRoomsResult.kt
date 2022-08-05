package com.ahtabbasi.roomlist.domain.models

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.utils.network.NetworkError

internal sealed class GetAllRoomsResult {
    class Success(val rooms: List<Room>) : GetAllRoomsResult()
    class Error(val error: NetworkError) : GetAllRoomsResult()
}