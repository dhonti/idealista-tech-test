package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.qualifiers.IODispatcher
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import com.dhontiveros.idealistatechtest.domain.usecases.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetRemotePropertyById @Inject constructor(
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
    private val repository: PropertyRepository
) : BaseUseCase<PropertyDetail, Int?>() {

    override suspend fun buildRequest(params: Int?): Flow<Resource<PropertyDetail>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(AppException.BadRequestException))
            }.flowOn(dispatcherIO)
        }
        return repository.getRemoteDetail(id = params).flowOn(dispatcherIO)
    }

}