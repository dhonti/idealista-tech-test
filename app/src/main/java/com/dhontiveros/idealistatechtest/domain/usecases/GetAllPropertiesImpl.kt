package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.qualifiers.IODispatcher
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import com.dhontiveros.idealistatechtest.presentation.usecases.GetAllProperties
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class GetAllPropertiesImpl @Inject constructor(
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
    private val repository: PropertyRepository
) : GetAllProperties() {

    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<PropertyListItem>>> =
        flow {
            val resourceRemoteList = repository.getAllProperties().filter { it !is Resource.Loading }.first()
            if( resourceRemoteList is Resource.Success ){
                val remoteList = resourceRemoteList.data
                val resourceLocalList = repository.getLocalFavProperties().filter { it !is Resource.Loading }.first()
                if( resourceLocalList is Resource.Success ){
                    val localList = resourceLocalList.data
                    val result = markFavoritesInRemoteList(remoteList, localList)
                    emit(Resource.Success(result))
                } else {
                    emit(resourceLocalList)
                }
            } else {
                emit(resourceRemoteList)
            }
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