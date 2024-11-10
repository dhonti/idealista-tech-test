package com.dhontiveros.idealistatechtest.domain.repository

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    // REMOTE:
    suspend fun getRemoteList(): Flow<Resource<List<PropertyListItem>>>
    suspend fun getRemoteDetail(id: Int): Flow<Resource<PropertyListItem>>

}