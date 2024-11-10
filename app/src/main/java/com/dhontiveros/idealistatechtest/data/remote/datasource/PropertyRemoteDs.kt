package com.dhontiveros.idealistatechtest.data.remote.datasource

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem

interface PropertyRemoteDs {

    suspend fun getRemoteList(): Resource<List<PropertyListItem>>

    suspend fun getRemoteDetail(id: Int): Resource<PropertyDetail>

}