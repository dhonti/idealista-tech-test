package com.dhontiveros.idealistatechtest.core.extensions

import android.database.sqlite.SQLiteException
import android.util.Log
import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException

suspend fun <T, E> callQueryDB(dbOperation: suspend () -> T, transform: (T) -> E): Resource<E> {
    return try {
        val response = dbOperation()
        val result = transform(response)
        Resource.Success(data = result)
    } catch (ex: SQLiteException) {
        Resource.Error(AppException.LocalDBException)
    }
}

suspend fun callOpDB(dbOperation: suspend () -> Unit): Resource<Boolean> {
    return try {
        dbOperation()
        Resource.Success(data = true)
    } catch (ex: SQLiteException) {
        Log.e("LocalExtensions", "ERROR: callOpDB(): ${ex.message}")
        Resource.Error(AppException.LocalDBException)
    }
}