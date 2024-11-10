package com.dhontiveros.idealistatechtest.data.repository

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.data.remote.datasource.PropertyRemoteDs
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val remoteDataSource: PropertyRemoteDs
) : PropertyRepository {

    override suspend fun getRemoteList(): Flow<Resource<List<PropertyListItem>>> = flow {
        emit(Resource.Loading)
        emit(remoteDataSource.getRemoteList())
    }

    override suspend fun getRemoteDetail(id: Int): Flow<Resource<PropertyDetail>> = flow {
        emit(Resource.Loading)
        emit(remoteDataSource.getRemoteDetail(id))
    }
}