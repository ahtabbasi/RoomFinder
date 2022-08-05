package com.ahtabbasi.roomlist.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.domain.models.GetAllRoomsResult
import com.ahtabbasi.roomlist.domain.usecases.GetAllRoomsUseCase
import com.ahtabbasi.roomlist.domain.usecases.SetBookedRoomUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RoomListViewModel @Inject constructor(
    private val getAllRooms: GetAllRoomsUseCase,
    private val setBookedRoom: SetBookedRoomUseCase,
) : ViewModel() {

    private val _viewState = MutableSharedFlow<RoomListViewState>(replay = 1)
    val viewState = _viewState.asSharedFlow()

    private val _loading = MutableSharedFlow<Boolean>(replay = 0)
    val loading = _loading.asSharedFlow()

    init {
        getRoomsList()
    }

    fun getRoomsList() {
        viewModelScope.launch {
            _loading.emit(true)
            val newState = when (val rooms = getAllRooms()) {
                is GetAllRoomsResult.Error -> RoomListViewState.Error(rooms.error)
                is GetAllRoomsResult.Success -> RoomListViewState.ShowRooms(rooms.rooms)
            }
            _viewState.emit(newState)
            _loading.emit(false)
        }
    }

    fun bookRoom(room: Room) {
        viewModelScope.launch {
            setBookedRoom(room)
            getRoomsList()
        }
    }
}