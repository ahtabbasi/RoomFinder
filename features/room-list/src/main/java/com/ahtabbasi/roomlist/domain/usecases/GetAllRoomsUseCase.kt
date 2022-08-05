package com.ahtabbasi.roomlist.domain.usecases

import com.ahtabbasi.roomlist.domain.models.GetAllRoomsResult

internal interface GetAllRoomsUseCase {
    suspend operator fun invoke(): GetAllRoomsResult
}
