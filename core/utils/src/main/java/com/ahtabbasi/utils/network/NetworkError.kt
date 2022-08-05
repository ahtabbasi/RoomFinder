package com.ahtabbasi.utils.network

import android.content.Context
import com.ahtabbasi.utils.R

sealed class NetworkError {
    object InternetNotAvailable : NetworkError()
    object TimedOut : NetworkError()
    object UnknownHost : NetworkError()
    object AuthorizationFailed : NetworkError()
    object ServerError : NetworkError()
    object Unknown : NetworkError()

    fun getErrorText(context: Context) = when (this) {
        AuthorizationFailed -> context.getString(R.string.authorization_error)
        InternetNotAvailable -> context.getString(R.string.no_internet)
        ServerError -> context.getString(R.string.server_error)
        TimedOut -> context.getString(R.string.time_out)
        Unknown -> context.getString(R.string.unknown_error)
        UnknownHost -> context.getString(R.string.unknown_host)
    }
}