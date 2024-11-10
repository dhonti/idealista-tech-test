package com.dhontiveros.idealistatechtest.domain.usecases.base

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseUseCase<Result, Params> {

    abstract suspend fun buildRequest(params: Params? = null): Flow<Resource<Result>>

    suspend fun execute(params: Params? = null): Flow<Resource<Result>> =
        try {
            buildRequest(params)
        } catch (ex: AppException) {
            flow { emit(Resource.Error(ex)) }
        }

}