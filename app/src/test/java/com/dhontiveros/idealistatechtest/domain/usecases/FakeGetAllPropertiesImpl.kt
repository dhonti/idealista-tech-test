package com.dhontiveros.idealistatechtest.domain.usecases

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.presentation.usecases.GetAllProperties
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//class FakeGetAllPropertiesImpl(
//    private val isSuccessful: Boolean? = true
//) : GetAllProperties() {
//    override suspend fun buildRequest(params: Nothing?): Flow<Resource<List<PropertyListItem>>> =
//        flow {
//            emit(Resource.Loading)
//            if (isSuccessful!!) {
//                emit(Resource.Success(data =))
//            } else {
//                emit(Resource.Error(exception = AppException.ServerErrorException))
//            }
//        }
//}