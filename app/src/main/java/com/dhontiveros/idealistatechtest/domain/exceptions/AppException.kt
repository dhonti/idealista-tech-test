package com.dhontiveros.idealistatechtest.domain.exceptions

sealed class AppException : Exception() {
    data object EmptyException : AppException()

    // REMOTE
    data object RequestException : AppException()
    data object BadRequestException : AppException()
    data object ConnectionException : AppException()
    data object ServerErrorException : AppException()

    // LOCAL
    data object LocalDBException : AppException()

    data object GeneralException : AppException()
}