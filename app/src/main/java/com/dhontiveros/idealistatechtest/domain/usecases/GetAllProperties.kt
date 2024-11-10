package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.qualifiers.IODispatcher
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import com.dhontiveros.idealistatechtest.domain.usecases.base.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllProperties @Inject constructor(
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
    private val repository: PropertyRepository
) : BaseUseCase<List<PropertyListItem>, Nothing>() {

    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<PropertyListItem>>> =
        flow {
            val resourceRemoteList = repository.getAllProperties().filter { it !is Resource.Loading }.first()
            val resourceLocalList = repository.getLocalFavProperties().filter { it !is Resource.Loading }.first()

            val remoteList = when (resourceRemoteList) {
                is Resource.Success -> resourceRemoteList.data
                is Resource.Error -> {
                    emit(Resource.Error(AppException.RequestException))
                    return@flow
                }

                else -> emptyList()
            }
            val localList = when (resourceLocalList) {
                is Resource.Success -> resourceLocalList.data
                else -> emptyList()
            }
            val result = markFavoritesInRemoteList(remoteList, localList)
            emit(Resource.Success(result))
        }.flowOn(dispatcherIO)


    private fun markFavoritesInRemoteList(
        remoteList: List<PropertyListItem>,
        localList: List<PropertyListItem>
    ): List<PropertyListItem> {
        val favoriteCodes = localList.filter { it.isFavorite }.map { it.propertyCode }.toSet()
        return remoteList.map { property ->
            if (property.propertyCode in favoriteCodes) {
                property.copy(isFavorite = true)
            } else {
                property
            }
        }
    }

}