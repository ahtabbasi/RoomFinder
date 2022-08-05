package com.ahtabbasi.domain.models

data class Room(
    val name: String,
    val spots: Int,
    val thumbnail: String,
    val isBooked: Boolean,
)