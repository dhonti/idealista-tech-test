package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.qualifiers.IODispatcher
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import com.dhontiveros.idealistatechtest.presentation.usecases.RemoveFavProperty
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoveFavPropertyImpl @Inject constructor(
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
    private val propertyRepository: PropertyRepository
) : RemoveFavProperty() {

    override suspend fun buildRequest(params: PropertyListItem?): Flow<Resource<Boolean>> {
        if (params == null) {
            return flow {
                emit(Resource.Error(AppException.BadRequestException))
            }.flowOn(dispatcherIO)
        }
        return propertyRepository.removeLocalFavProperty(item = params).flowOn(dispatcherIO)
    }

}