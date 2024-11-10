package com.dhontiveros.idealistatechtest.data.local.datasource

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.data.local.entity.PropertyLocalModel
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem

interface PropertyLocalDs {

    suspend fun getAll(): Resource<List<PropertyListItem>>
    suspend fun insert(user: PropertyLocalModel): Resource<Boolean>
    suspend fun remove(user: PropertyLocalModel): Resource<Boolean>

}