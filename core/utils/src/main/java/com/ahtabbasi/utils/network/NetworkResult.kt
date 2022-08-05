package com.ahtabbasi.utils.network

sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Failure<T>(val error: NetworkError) : NetworkResult<T>()
}