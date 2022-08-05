package com.ahtabbasi.roomfinder.navigation

import android.view.View
import androidx.navigation.findNavController
import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomdetail.RoomDetailUIModel
import com.ahtabbasi.roomfinder.AppNavigationDirections
import com.ahtabbasi.roomlist.presentation.navigation.RoomListNavigator

internal class AppNavigator : RoomListNavigator {

    override fun navigateToRoomDetail(view: View, room: Room) {
        navigateToRoomDetailImpl(view, room)
    }

    private fun navigateToRoomDetailImpl(view: View, room: Room) {
        val action = AppNavigationDirections.actionNavigateToRoomDetail(
            RoomDetailUIModel.fromDomainModel(room)
        )
        view.findNavController().navigate(action)
    }

}