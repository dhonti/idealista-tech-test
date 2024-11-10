package com.dhontiveros.idealistatechtest.domain.repository

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.data.local.entity.PropertyLocalModel
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    // REMOTE:
    suspend fun getRemoteList(): Flow<Resource<List<PropertyListItem>>>
    suspend fun getRemoteDetail(id: Int): Flow<Resource<PropertyDetail>>

    // LOCAL:
    suspend fun getLocalFavProperties(): Flow<Resource<List<PropertyListItem>>>
    suspend fun insertLocalFavProperty(item: PropertyListItem): Flow<Resource<Boolean>>
    suspend fun removeLocalFavProperty(item: PropertyListItem): Flow<Resource<Boolean>>

}