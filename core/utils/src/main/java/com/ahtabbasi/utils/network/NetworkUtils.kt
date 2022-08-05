package com.ahtabbasi.utils.network

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkUtils {

    private const val HTTP_ERROR_CODE_UNAUTHORIZED = 401
    private const val HTTP_ERROR_CODE_TIMED_OUT = 408
    private const val HTTP_ERROR_CODE_SERVER_ERROR = 500

    fun getNetworkError(e: Exception): NetworkError = when (e) {
        is HttpException -> getNetworkError(e.code())
        is SocketTimeoutException -> NetworkError.TimedOut
        is UnknownHostException -> NetworkError.UnknownHost
        is IOException -> NetworkError.InternetNotAvailable
        else -> NetworkError.Unknown
    }

    private fun getNetworkError(httpCode: Int): NetworkError = when (httpCode) {
        // User unauthorised error
        HTTP_ERROR_CODE_UNAUTHORIZED -> NetworkError.AuthorizationFailed
        // Time out error
        HTTP_ERROR_CODE_TIMED_OUT -> NetworkError.TimedOut
        // Internal server error
        HTTP_ERROR_CODE_SERVER_ERROR -> NetworkError.ServerError
        // Any other error executing the API
        else -> NetworkError.Unknown
    }
}

