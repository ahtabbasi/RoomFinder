package com.ahtabbasi.roomlist.domain.usecases

import com.ahtabbasi.roomlist.domain.repositories.RoomRepository
import javax.inject.Inject

internal class GetAllRoomsUseCaseImpl @Inject constructor(
    private val roomRepository: RoomRepository,
) : GetAllRoomsUseCase {

    override suspend fun invoke() = roomRepository.getAllRooms()

}