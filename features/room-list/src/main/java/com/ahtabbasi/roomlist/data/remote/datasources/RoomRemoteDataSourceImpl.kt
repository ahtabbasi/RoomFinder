package com.ahtabbasi.roomlist.data.remote.datasources

import com.ahtabbasi.roomlist.data.remote.api.ApiService
import com.ahtabbasi.roomlist.data.remote.models.RoomsListDTO
import com.ahtabbasi.utils.network.NetworkResult
import com.ahtabbasi.utils.network.NetworkUtils
import javax.inject.Inject

internal class RoomRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : RoomRemoteDataSource {

    override suspend fun getAllRooms(): NetworkResult<RoomsListDTO> {
        return try {
            NetworkResult.Success(apiService.getAllRooms())
        } catch (e: Exception) {
            NetworkResult.Failure(NetworkUtils.getNetworkError(e))
        }
    }
}