package com.ahtabbasi.roomlist.data.remote.api

import com.ahtabbasi.roomlist.data.remote.models.RoomsListDTO
import retrofit2.http.GET

internal interface ApiService {

    @GET("/rooms.json")
    suspend fun getAllRooms(): RoomsListDTO

}