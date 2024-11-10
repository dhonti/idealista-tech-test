package com.dhontiveros.idealistatechtest.domain.exceptions

import com.dhontiveros.idealistatechtest.core.common.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend inline fun <T, E> callApi(
    dispatcher: CoroutineDispatcher,
    crossinline apiRequest: suspend () -> Response<T>,
    crossinline transform: (T) -> E
): Resource<E> {
    return withContext(dispatcher) {
        try {
            val response = apiRequest()
            if (response.isSuccessful) {
                val result = transform(response.body()!!)
                Resource.Success(data = result)
            } else {
                Resource.Error(AppException.GeneralException)
            }
        } catch (e: HttpException) {
            Resource.Error(AppException.RequestException)
        } catch (ex: IOException) {
            Resource.Error(AppException.ConnectionException)
        } catch (ex: Exception) {
            Resource.Error(AppException.GeneralException)
        }
    }
}