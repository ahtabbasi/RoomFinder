package com.ahtabbasi.roomlist.domain.usecases

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.domain.repositories.RoomRepository
import javax.inject.Inject

internal class SetBookedRoomUseCaseImpl @Inject constructor(
    private val roomRepository: RoomRepository,
) : SetBookedRoomUseCase {

    override suspend fun invoke(room: Room) = roomRepository.bookRoom(room)

}