package com.ahtabbasi.roomlist.presentation.view

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.utils.network.NetworkError

internal sealed class RoomListViewState {
    class ShowRooms(val rooms: List<Room>) : RoomListViewState()
    class Error(val error: NetworkError) : RoomListViewState()
}