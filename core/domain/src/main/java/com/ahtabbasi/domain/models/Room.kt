package com.ahtabbasi.domain.models

data class Room(
    val name: String,
    val spots: Int,
    val photo: String,
    val isBooked: Boolean,
)