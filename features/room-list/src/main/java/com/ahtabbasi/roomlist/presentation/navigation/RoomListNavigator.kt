package com.ahtabbasi.roomlist.presentation.navigation

import android.view.View
import com.ahtabbasi.domain.models.Room

interface RoomListNavigator {
    fun navigateToRoomDetail(view: View, room: Room)
}