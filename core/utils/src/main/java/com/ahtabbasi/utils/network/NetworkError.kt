package com.ahtabbasi.utils.network

sealed class NetworkError {
    object InternetNotAvailable : NetworkError()
    object TimedOut : NetworkError()
    object UnknownHost : NetworkError()
    object AuthorizationFailed : NetworkError()
    object ServerError : NetworkError()
    object Unknown : NetworkError()
}