package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.qualifiers.IODispatcher
import com.dhontiveros.idealistatechtest.domain.repository.PropertyRepository
import com.dhontiveros.idealistatechtest.presentation.usecases.GetFavProperties
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavPropertiesImpl @Inject constructor(
    @IODispatcher private val dispatcherIO: CoroutineDispatcher,
    private val repository: PropertyRepository
) : GetFavProperties() {

    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<PropertyListItem>>> =
        repository.getLocalFavProperties().flowOn(dispatcherIO)

}