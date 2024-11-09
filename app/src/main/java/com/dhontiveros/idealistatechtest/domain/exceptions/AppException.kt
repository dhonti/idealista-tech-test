package com.dhontiveros.idealistatechtest.domain.exceptions

sealed class AppException : Exception() {
    object EmptyException : AppException()

    // USER:
    object CheckRemoveUserException: AppException()

    // REMOTE
    object RequestException : AppException()
    object BadRequestException : AppException()
    object ConnectionException : AppException()
    object ServerErrorException : AppException()

    // LOCAL
    object LocalDBException : AppException()

    object GeneralException : AppException()
}