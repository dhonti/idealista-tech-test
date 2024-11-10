package com.dhontiveros.idealistatechtest.data.repository

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.data.local.datasource.PropertyLocalDs
import com.dhontiveros.idealistatechtest.data.local.entity.PropertyLocalModel
import com.dhontiveros.idealistatechtest.data.remote.datasource.PropertyRemoteDs
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.models.toLocalData
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val remoteDataSource: PropertyRemoteDs,
    private val localDataSource: PropertyLocalDs
) : PropertyRepository {

    override suspend fun getAllProperties(): Flow<Resource<List<PropertyListItem>>> = flow {
        emit(Resource.Loading)
        emit(remoteDataSource.getRemoteList())
    }

    override suspend fun getRemoteDetail(id: Int): Flow<Resource<PropertyDetail>> = flow {
        emit(Resource.Loading)
        emit(remoteDataSource.getRemoteDetail(id))
    }

    override suspend fun getLocalFavProperties(): Flow<Resource<List<PropertyListItem>>> = flow {
        emit(Resource.Loading)
        emit(localDataSource.getAll())
    }

    override suspend fun insertLocalFavProperty(item: PropertyListItem): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading)
            emit(localDataSource.insert(item.toLocalData()))
        }

    override suspend fun removeLocalFavProperty(item: PropertyListItem): Flow<Resource<Boolean>> =
        flow {
            emit(Resource.Loading)
            emit(localDataSource.remove(item.toLocalData()))
        }
}