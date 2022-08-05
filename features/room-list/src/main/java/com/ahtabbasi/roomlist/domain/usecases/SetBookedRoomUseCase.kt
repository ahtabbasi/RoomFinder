package com.ahtabbasi.roomlist.domain.usecases

import com.ahtabbasi.domain.models.Room

internal interface SetBookedRoomUseCase {
    suspend operator fun invoke(room: Room)
}
