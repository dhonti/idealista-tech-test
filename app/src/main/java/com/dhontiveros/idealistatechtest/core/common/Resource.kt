package com.dhontiveros.idealistatechtest.core.common

import com.dhontiveros.idealistatechtest.domain.exceptions.AppException


sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: AppException) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
    object Empty : Resource<Nothing>()
}