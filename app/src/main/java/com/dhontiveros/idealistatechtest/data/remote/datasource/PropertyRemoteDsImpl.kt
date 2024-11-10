package com.dhontiveros.idealistatechtest.data.remote.datasource

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.core.extensions.callApi
import com.dhontiveros.idealistatechtest.data.remote.api.ApiService
import com.dhontiveros.idealistatechtest.data.remote.model.toDomain
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.qualifiers.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PropertyRemoteDsImpl @Inject constructor(
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
    private val apiService: ApiService,
) : PropertyRemoteDs {

    override suspend fun getRemoteList(): Resource<List<PropertyListItem>> = callApi(
        dispatcherIO,
        apiRequest = { apiService.getPropertiesList() },
        transform = { list ->
            list.map { it.toDomain() }
        }
    )

    override suspend fun getRemoteDetail(id: Int): Resource<PropertyDetail> = callApi(
        dispatcherIO,
        apiRequest = { apiService.getDetailProperty() },
        transform = { it.toDomain() }
    )
}